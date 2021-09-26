package com.example.learnspringreactive.handler;

import com.example.learnspringreactive.controller.dao.CustomerDao;
import com.example.learnspringreactive.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers1(ServerRequest serverRequest) {
        return ServerResponse.ok().body(customerDao.getCustomersFlux1(), Customer.class);
    }

    public Mono<ServerResponse> loadCustomersStream(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customerDao.getCustomersFlux1(), Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(customerDao.getCustomersFlux1()
                        .filter(c -> c.getId() == Integer.parseInt(serverRequest.pathVariable("input"))).next(),Customer.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
        serverRequest.bodyToMono(Customer.class).map(c -> c.getId() + ":" + c.getName()),String.class);
    }
}
