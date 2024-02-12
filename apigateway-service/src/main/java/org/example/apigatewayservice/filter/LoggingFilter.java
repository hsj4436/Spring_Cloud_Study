package org.example.apigatewayservice.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Config {
        // Put configuration properties if present
        // Get properties from application.yml or application.properties
        // Or get properties from Configuration server
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    @Override
    public GatewayFilter apply(LoggingFilter.Config config) {
//        return ((exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Logging filter base message : {}", config.getBaseMessage());
//
//            if (config.isPreLogger()) {
//                log.info("Logging PRE filter : request id -> {}", request.getId());
//            }
//
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                if (config.isPostLogger()) {
//                    log.info("Logging POST filter : response code -> {}", response.getStatusCode());
//                }
//            }));
//        });
        GatewayFilter filter = new OrderedGatewayFilter(((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging filter base message : {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info("Logging PRE filter : request id -> {}", request.getId());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.isPostLogger()) {
                    log.info("Logging POST filter : response code -> {}", response.getStatusCode());
                }
            }));
        }), Ordered.LOWEST_PRECEDENCE);
        // Ordered.HIGHEST_PRECEDENCE로 순서를 지정하면 default-filter로 지정한 것 보다도 먼저 수행됨
        return filter;
    }
}
