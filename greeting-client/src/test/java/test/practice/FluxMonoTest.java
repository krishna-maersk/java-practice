package test.practice;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class FluxMonoTest {

//    1. switchIfEmpty

//    2. Deferring the execution of publisher mon/flux
//    3. Error handling reactive streams
//          if you want recover before subscribe add onErrorResume it will work when they sent reactive error
//          suppose source sent exception directly, then we have to convert exception to mono



    @Test
    void ShouldHandleError() {

        Mono<Integer> data = Mono.just(1).flatMap(index -> getData())
                .map(i -> i + 1)
                .onErrorResume(e -> {
                    System.out.println("in onErrorResume");
                    return Mono.just(-1);
                });

        data.subscribe(number -> System.out.println(number), e -> System.err.println("Error: " + e ), () -> System.out.println("Complete"));
    }

    private Mono<Integer> getData() {
        Integer number = 99;
//        return Mono.error(new RuntimeException("something went wrong"));
        throw new RuntimeException("Something went wrong ");
//        return Mono.just(number);
    }


    @Test
    void shouldDeferThePublisher() throws InterruptedException {
        Mono<LocalDateTime> currentTime = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        currentTime.subscribe(t -> System.out.println(t));
        Thread.sleep(2000);
        currentTime.subscribe(t -> System.out.println(t));
        Thread.sleep(2000);
        currentTime.subscribe(t -> System.out.println(t));
        Thread.sleep(2000);
        currentTime.subscribe(t -> System.out.println(t));

    }

    @Test
    void shouldVerifySwitchIfEmpty() {
//        Mono<String> hello = Mono.just("Hello")
        Mono<String> hello = Mono.empty()
                .map(s -> s + " World!")
                .switchIfEmpty(Mono.defer(() -> fallback()));
        hello.subscribe(s -> System.out.println(s));
//        System.out.println(hello);
    }

    private Mono<String> fallback() {
        System.out.println("Fallback");
        return Mono.just("Hi");
    }


}
