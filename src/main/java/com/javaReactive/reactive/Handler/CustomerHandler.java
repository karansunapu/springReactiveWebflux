package com.javaReactive.reactive.Handler;

import com.javaReactive.reactive.dao.CustomerDao;
import com.javaReactive.reactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomers(ServerRequest request){
        Flux<Customer> customersWithoutDelay = customerDao.getAllCustomersStreamWithoutDelay();
        return ServerResponse.ok()
                .body(customersWithoutDelay, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
        // gives string convert to int
        int customerId = Integer.valueOf(request.pathVariable("customerId"));
        // gives flux convert to mono -> .next() / .take(1).single()
        Mono<Customer> customerMono = customerDao.getAllCustomersStreamWithoutDelay()
                                                .filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok()
                .body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> str = customerMono.map(c-> c.getId()+" : "+c.getName());
        return ServerResponse.ok()
                .body(str, String.class);
    }
}
