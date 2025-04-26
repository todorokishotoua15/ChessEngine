package com.example.gateway.gateway.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.gateway.gateway.filters.AuthenticationFilter;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        GatewayFilter authFilter = authenticationFilter.apply(new AuthenticationFilter.Config());
        return builder.routes()
                // .route(
                // p -> p.path("/game-service/**")
                // .filters(spec -> spec.filter(loggingFilter).filter(authFilter))
                // .uri("lb://game-service"))
                .route(
                        p -> p.path("/authservice/**")
                                .uri("lb://authservice"))
                .build();
    }
}
