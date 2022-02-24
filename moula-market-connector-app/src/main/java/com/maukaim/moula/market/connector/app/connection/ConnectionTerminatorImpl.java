package com.maukaim.moula.market.connector.app.connection;


import com.maukaim.moula.market.connector.app.connection.model.ConnectionTimeout;
import com.maukaim.moula.market.connector.app.connection.model.TerminationRequest;
import com.maukaim.moula.market.connector.app.request.model.DataRequest;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class ConnectionTerminatorImpl implements ConnectionTerminator {

    private final ScheduledExecutorService executor = ConnectionTerminatorConfig.getExecutor();

    private Map<DataRequest, ConnectionTimeout> schedules = new ConcurrentHashMap<>();


    @Override
    public ConnectionTimeout scheduleTermination(TerminationRequest request) {

        this.schedules.computeIfPresent(request.dataRequest(), new TerminateNow());

        ScheduledFuture<?> schedule = this.executor.schedule(new ClosingTask(request),
                request.timeoutMS(), TimeUnit.MILLISECONDS);
        ConnectionTimeout connectionTimeout = new ConnectionTimeout(request, schedule);
        this.schedules.put(request.dataRequest(), connectionTimeout);
        return connectionTimeout;
    }


    @Override
    public Optional<ConnectionTimeout> rollIfHas(DataRequest dataRequest) {
        ConnectionTimeout connectionTimeout = this.schedules.get(dataRequest);
        if (connectionTimeout != null) {
            boolean isCancelled = connectionTimeout.terminationEvent().cancel(false);
            if (isCancelled) {
                this.schedules.remove(dataRequest);
                return Optional.of(this.scheduleTermination(connectionTimeout.terminationRequest()));
            }
        }

        return Optional.empty();

    }


    private class ClosingTask implements Runnable {

        private TerminationRequest terminationRequest;

        public ClosingTask(TerminationRequest terminationRequest) {
            this.terminationRequest = terminationRequest;
        }

        @Override
        public void run() {
            try {
                System.out.println("Stopping!" + Thread.currentThread().getName());
                terminationRequest.closeable().close();
                schedules.remove(terminationRequest.dataRequest());
            } catch (IOException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
    }

    private class TerminateNow implements BiFunction<DataRequest, ConnectionTimeout, ConnectionTimeout> {

        private static final long WAIT_TIMEOUT_MS = 3000;

        @Override
        public ConnectionTimeout apply(DataRequest dr, ConnectionTimeout ce) {
            ScheduledFuture<?> event = ce.terminationEvent();
            if (!event.cancel(false)) {
                try {
                    event.wait(WAIT_TIMEOUT_MS);
                } catch (InterruptedException ignored) {
                }
                if (event.isDone()) {
                    return null;
                }
                event.cancel(true);
            }

            TerminationRequest previousCr = ce.terminationRequest();
            TerminationRequest newCr = new TerminationRequest(dr, 0L, previousCr.closeable());
            executor.execute(new ClosingTask(newCr));
            return null;
        }
    }

}
