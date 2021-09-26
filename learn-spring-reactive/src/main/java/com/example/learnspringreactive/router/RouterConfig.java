package com.example.learnspringreactive.router;

import com.example.learnspringreactive.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route()
                .GET("/router/customers/", customerHandler::loadCustomers1)
                .GET("/router/customers/stream",customerHandler::loadCustomersStream)
                .GET("/router/customers/{input}",customerHandler::findCustomer)
                .POST("/router/customers/save",customerHandler::save)
                .build();

    }


}
