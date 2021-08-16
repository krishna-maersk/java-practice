package com.example.rsocketgreetingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@SpringBootApplication
public class RsocketGreetingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsocketGreetingServiceApplication.class, args);
    }

}

@Component
@RequiredArgsConstructor
class JsonHelper{
    private  final ObjectMapper objectMapper;

    @SneakyThrows
    <T> T read (String json, Class<T> clazz){
        return this.objectMapper.readValue(json,clazz);
    }

    @SneakyThrows
    String write(Object o){
        return this.objectMapper.writeValueAsString(o);
    }
}

@Component
@RequiredArgsConstructor
@Log4j2
class Producer{
    private final JsonHelper jsonHelper;

    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        log.info("starting producer....");

        SocketAcceptor socketAcceptor = new SocketAcceptor() {
            @Override
            public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
//                AbstractRSocket
                return null;
            }
        };
    }
}

class GreetingService{

    Flux<GreetingResponse> greet(GreetingRequest greetingRequest){

        return Flux.fromStream(Stream.generate(() -> new GreetingResponse("Hello " + greetingRequest.getName() + " @ " + Instant.now())))
                .delayElements(Duration.ofSeconds(1));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingRequest{
    private String name;
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingResponse{
    private String message;
}
