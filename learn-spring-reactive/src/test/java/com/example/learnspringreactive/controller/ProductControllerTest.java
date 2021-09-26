package com.example.learnspringreactive.controller;

import com.example.learnspringreactive.dto.ProductDto;
import com.example.learnspringreactive.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    public void addProductTest(){
        Mono<ProductDto> productDtoMono = Mono.just(new ProductDto("id1","TV",1,10000));
        when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);

        webTestClient.post()
                .uri("/products/")
                .body(Mono.just(productDtoMono), ProductDto.class)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    public void getProductsTest(){
        ProductDto productDto = new ProductDto("id1", "TV", 1, 10000);
        ProductDto productDto1 = new ProductDto("id2", "Mobile", 1, 10000);
        Flux<ProductDto> productDtoFlux = Flux.just(productDto, productDto1);
        when(productService.getProducts()).thenReturn(productDtoFlux);

        Flux<ProductDto> responseBody = webTestClient.get()
                .uri("/products/")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(productDto)
                .expectNext(productDto1)
                .verifyComplete();

    }

    @Test
    public void getProductTest(){
        ProductDto productDto = new ProductDto("id1", "TV", 1, 10000);
        Mono<ProductDto> productDtoMono = Mono.just(productDto);
        when(productService.getProduct("id1")).thenReturn(productDtoMono);

        Flux<ProductDto> responseBody = webTestClient.get()
                .uri("/products/id1")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getName().equals("TV"))
                .verifyComplete();

    }


    @Test
    public void updateProductTest(){
        ProductDto productDto = new ProductDto("id1", "TV", 1, 10000);
        Mono<ProductDto> productDtoMono = Mono.just(productDto);
        when(productService.updateProduct(productDtoMono,"id1")).thenReturn(productDtoMono);
        webTestClient.put()
                .uri("/products/id1")
                .exchange()
                .expectStatus()
                .isOk();
    }


    @Test
    public void deleteProductTest(){
        when(productService.deleteProduct("id1")).thenReturn(Mono.empty());
        webTestClient.delete()
                .uri("/products/id1")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
