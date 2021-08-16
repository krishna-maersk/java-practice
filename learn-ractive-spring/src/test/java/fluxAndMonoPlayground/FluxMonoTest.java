package fluxAndMonoPlayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxMonoTest {

    @Test
    void fluxTest() {
        Flux<String> stringFlux = Flux.just("Krishna", "Spring", "Reactive ")
                .concatWith(Flux.error(new RuntimeException("Testing ")))
                .concatWith(Flux.just("After error"))
                .log();
        stringFlux.subscribe(System.out::println, System.err::println, System.out::println);
    }

    @Test
    void fluxTestWithoutError() {
        Flux<String> stringFlux = Flux.just("Krishna", "Spring", "Reactive").log();
        StepVerifier.create(stringFlux)
                .expectNext("Krishna")
                .expectNext("Spring")
                .expectNext("Reactive")
                .verifyComplete();
    }

    @Test
    void fluxTestWithError() {
        Flux<String> stringFlux = Flux.just("Krishna", "Spring", "Reactive")
                .concatWith(Flux.error(new RuntimeException("Testing")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Krishna")
                .expectNext("Spring")
                .expectNext("Reactive")
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Testing")
                .verify();
    }


    @Test
    void fluxTestElementsCountWithError() {
        Flux<String> stringFlux = Flux.just("Krishna", "Spring", "Reactive")
                .concatWith(Flux.error(new RuntimeException("Testing")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Testing")
                .verify();
    }

    @Test
    void fluxTestWithErrorAtTime() {
        Flux<String> stringFlux = Flux.just("Krishna", "Spring", "Reactive")
                .concatWith(Flux.error(new RuntimeException("Testing")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Krishna", "Spring", "Reactive")
                .expectErrorMessage("Testing")
                .verify();
    }

    @Test
    void MonoTest() {
        Mono<String> stringMono = Mono.just("Krishna").log();

        StepVerifier.create(stringMono).
                expectNext("Krishna")
                .verifyComplete();

    }

    @Test
    void MonoTestWithError() {
        Mono<Object> stringMono = Mono.error(new RuntimeException("Testing")).log();

        StepVerifier.create(stringMono).
                expectError(RuntimeException.class)
                .verify();

    }

}
