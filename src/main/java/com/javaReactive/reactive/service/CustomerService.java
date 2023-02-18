package com.javaReactive.reactive.service;

import com.javaReactive.reactive.dao.CustomerDao;
import com.javaReactive.reactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    // Runs even if we cancel the request
    // Returns result ony after all data is prepared
    public List<Customer> getCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getAllCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customers;
    }
    public Flux<Customer> getCustomersStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customersFLuxStream = customerDao.getAllCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return customersFLuxStream;
    }


}
