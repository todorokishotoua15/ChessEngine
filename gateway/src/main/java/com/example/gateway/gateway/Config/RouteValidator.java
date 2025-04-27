package com.example.gateway.gateway.Config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
        public static final List<String> openApiEndpoints = List.of(
                        "/authservice/register",
                        "/authservice/validate",
                        "/authservice/authenticate",
                        "/authservice/**",
                        "/gameservice/*",
                        "eureka");

        public Predicate<ServerHttpRequest> isSecured = serverHttpRequest -> openApiEndpoints
                        .stream()
                        .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}