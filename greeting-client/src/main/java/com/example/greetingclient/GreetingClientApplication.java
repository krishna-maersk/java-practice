package com.example.greetingclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GreetingClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingClientApplication.class, args);
    }

    @Bean
    WebClient webClient(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:8080")
//                .filter(ExchangeFilterFuncti)
                .build();
    }

}
@RequiredArgsConstructor
@Component
@Log4j2
class Client {
    private final WebClient client;

    @EventListener(ApplicationReadyEvent.class)
    public void ready(){
        var name = "Spring Fans";

        this.client
                .get()
                .uri("/greeting/{name}", name )
                .retrieve()
                .bodyToMono(GreetingResponse.class)
                .map(GreetingResponse::getMessage)
                .onErrorMap(throwable -> new IllegalArgumentException( " original msg: " + throwable.toString() ))
                .onErrorResume(IllegalArgumentException.class, ex -> Mono.just(ex.toString()))
                .subscribe(gr -> log.info("MONO: " + gr));


//        this.client
//                .get()
//                .uri("/greetings/{name}", name )
//                .retrieve()
//                .bodyToFlux(GreetingResponse.class)
//                .subscribe(gr -> log.info("Flux: " + gr.getMessage()));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingResponse {
    private String message;
}
