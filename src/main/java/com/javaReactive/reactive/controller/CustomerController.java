package com.javaReactive.reactive.controller;

import com.javaReactive.reactive.dto.Customer;
import com.javaReactive.reactive.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }


    // Instead of a single jsonArray object we send individual Customer Objects/Events
    // @GetMapping("/fluxStream") -> would have worked same
    // but this works different
    // 1. Synchronous + Non Blocking
    // 2. Immediately Processing is stopped as soon as subscriber cancels the request
    // (browser cancel) ….request does not goes to the backend …
    // in traditional approach still backend processes the request even after subscriber cancels the request
    @GetMapping(value = "/fluxStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getCustomersFluxStream() {
        return customerService.getCustomersStream();
    }
}
