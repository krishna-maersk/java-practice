package com.example.greetingservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

//@SpringBootTest
class GreetingServiceApplicationTests {

//    @Test
//    void contextLoads() {
//
//    }

    @Test
    void simpleFlux(){
        Flux.just("Spring", "Spring Reactive", "Spring core")
                .map(c -> c+ " Flux").subscribe(System.out::println);
    }

}
