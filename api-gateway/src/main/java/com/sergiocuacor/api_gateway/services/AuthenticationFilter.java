package com.sergiocuacor.api_gateway.services;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouterValidator validator;
    private final JwtUtils jwtUtils;

    public AuthenticationFilter(RouterValidator validator, JwtUtils jwtUtils) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            System.out.println("Path: " + request.getPath());
            // Priemr check: ¿requiere autenticación o no?
            if(!validator.isSecured.test(request)){
                System.out.println("Ruta no segura");
                return chain.filter(exchange); // Si no requiere autenticación pasa automáticamente al siguiente filtro(en este caso al microservicio correspondiente
            }

            // Segundo check: ¿Tiene la cabecera Authorization?
            if(authMissing(request)){ // Si requiere autorización pero NO tiene el header Authorization tiramos 401
                System.out.println("Falta header de autorización");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            System.out.println("Auth header: " + authHeader);
            // Tercer check: Nos aseguramos de que no sea null y empiece por Bearer
            if( authHeader == null || !authHeader.startsWith("Bearer ")){
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }


            // Cuarto check: pillamos el token quitando "Bearer " y comprobamos que no haya EXPIRADO
            String token = authHeader.substring(7);
            System.out.println("Token extraído: "+ token);
            if (jwtUtils.isExpired(token)) {
                System.out.println("Token expirado");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            // Quinto check: ¿podemos extraer el userId?
            Integer userId = jwtUtils.extractUserId(token);
            System.out.println("UserId extraído: "+ userId);
            if (userId == null) {
                System.out.println("No se pudo extraer UserId");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }


            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-User-Id", userId.toString())
                    .build();

            System.out.println("Request modificada con userId: " + userId);

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        // Devolvemos true si la auth está missing, es decir si no tiene el header Authorization
        return !request.getHeaders().containsKey("Authorization");
    }

    public static class Config{}
}
