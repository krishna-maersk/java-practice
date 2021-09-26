package com.example.learnspringreactive.controller.dao;

import com.example.learnspringreactive.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers()  {
        return IntStream.range(1, 10)
                .peek(CustomerDao::getSleep)
                .peek(i -> System.out.println("processing coount " + i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList());
    }

    private static void getSleep(int i)  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Flux<Customer> getCustomersFlux() {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing " + i))
                .map(i -> new Customer(i, "customer"+i));
    }

    public Flux<Customer> getCustomersFlux1() {
        return Flux.range(1,10)
                .doOnNext(i -> System.out.println("processing " + i))
                .map(i -> new Customer(i, "customer"+i));
    }
}
