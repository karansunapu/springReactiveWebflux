package com.javaReactive.reactive.Handler;

import com.javaReactive.reactive.dao.CustomerDao;
import com.javaReactive.reactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomersStream(ServerRequest request){
        Flux<Customer> allCustomersStream = customerDao.getAllCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allCustomersStream, Customer.class);
    }
}
