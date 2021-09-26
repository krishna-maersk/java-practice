package com.example.debuggingreactor;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.ReactorBlockHoundIntegration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.tools.agent.ReactorDebugAgent;

import java.time.Duration;
import java.util.function.Consumer;

@Log4j2
@SpringBootApplication
public class DebuggingReactorApplication {
//    private static final Logger log = LogManager.getLogger(DebuggingReactorApplication.class);

    public static void main(String[] args) {
//        Hooks.onOperatorDebug();
//        ReactorDebugAgent.init();
//        ReactorDebugAgent.processExistingClasses();
        BlockHound.install();
        SpringApplication.run(DebuggingReactorApplication.class, args);
    }

    void info(String l) {
        log.info("Info Thread: " + Thread.currentThread().getName());
        log.info("letter " + l);
    }

    void error(Throwable t) {
        log.info("Error Thread: " + Thread.currentThread().getName());
        log.error("Oh No!");
        log.error(t);
    }

    void processPublisher(Flux<String> letters) {
        letters.subscribe(this::info);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void go() throws Exception {
        Scheduler cantBlock = Schedulers.newParallel("p5", 5);

        Flux<String> letters = Flux.just("A", "B", "C", "D", "E", "F")
                .checkpoint("Capital letters ", true)
                .map(String::toLowerCase)
                .checkpoint("Lower letters ", false)
//                .flatMap(l -> {
//                    if (l.equals("f")) {
//                        return Mono.error(new IllegalLetterException());
//                    } else return Mono.just(l);
//                })
//                .map(l -> {
//                    if (l.equals("f")) {
//                        throw new IllegalLetterException();
//                    }
//                    return l;
//                })
                .flatMap(Mono::just)
                .doOnNext((x) -> block())
                .checkpoint("I want an error to show this message ", false)
//                .subscribeOn(Schedulers.elastic(), true)
                .subscribeOn(cantBlock, true)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .doOnError(this::error);

//        Consumer<String> stringConsumer = l -> log.info("new letter" + l);
//        Flux<String> share = letters.share();

//        letters.subscribe(this::info, this::error);
        processPublisher(letters);
//        Thread.sleep(2 *1000);
//        share.subscribe(stringConsumer);
    }

    @SneakyThrows
    void block() {
        Thread.sleep(1000);
    }
}
