package com.example.learnspringreactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    void testMono() {
        Mono<?> mono = Mono.just("Krishna")
                .then(Mono.error(new RuntimeException("Exception happened")))
                .log();
        mono.subscribe(System.out::println, System.out::println);
    }

    @Test
    public void testFlux() {
        Flux<String> flux = Flux.just("Spring", "SpringBoot", "Reactive")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception happened")))
                .concatWithValues("DB")
                .log();
        flux.subscribe(System.out::println, System.out::println);
    }

}
