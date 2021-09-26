package com.example.learnspringreactive.service;

import com.example.learnspringreactive.controller.dao.CustomerDao;
import com.example.learnspringreactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers()  {
        long startt = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total time : " + (end-startt));
        return customers;

    }

    public Flux<Customer> getCustomersStream() {
        long startt = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersFlux();
        long end = System.currentTimeMillis();
        System.out.println("Total time : " + (end-startt));
        return customers;
    }
}
