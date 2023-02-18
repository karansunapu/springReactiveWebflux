package com.javaReactive.reactive.dao;


import com.javaReactive.reactive.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepAction(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // make a STREAM of  1-50 Customer Objects
    // with delay of 1 sec in each Customer
    public List<Customer> getAllCustomers(){
        return IntStream.rangeClosed(1, 25)
                .peek(CustomerDao::sleepAction)
                .peek(i -> System.out.println("processing count : " + i))
                .mapToObj(i -> new Customer(i, "Customer_" + i))
                .collect(Collectors.toList());
    }


    // make a FLUX STREAM of  1-50 Customer Objects
    // with delay of 1 sec in each Customer
    public Flux<Customer> getAllCustomersStream(){
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count : " + i))
                .map( i -> new Customer(i, "Customer_"+i));
    }

    //without any delay Flux stream
    public Flux<Customer> getAllCustomersStreamWithoutDelay(){
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("processing count : " + i))
                .map( i -> new Customer(i, "Customer_"+i));
    }
}
