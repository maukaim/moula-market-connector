package com.maukaim.moula.market.connector.web.api.swagger.config;

@FunctionalInterface
public interface SwaggerConfigCustomizer {

    /**
     *  Allow to customize the swagger configuration properties provided by the user.
     *
     * @param swaggerProperties , the configuration properties to customize.
     */
    void customize(MaukaimSwaggerProperties swaggerProperties);
}
