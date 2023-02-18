package com.javaReactive.reactive.Router;

import com.javaReactive.reactive.Handler.CustomerHandler;
import com.javaReactive.reactive.Handler.CustomerStreamHandler;
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
    @Autowired
    private CustomerStreamHandler customerStreamHandler;

    // make it a bean
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers", customerHandler::getCustomers)
                .GET("/router/customers/stream", customerStreamHandler::getCustomersStream)
                .GET("/router/customers/{customerId}",customerHandler::findCustomer)
                .POST("/router/customers/saveCustomer", customerHandler::saveCustomer)
                .build();
    }
}
