package com.example.learnspringreactive.controller;

import com.example.learnspringreactive.dto.ProductDto;
import com.example.learnspringreactive.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public Flux<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/range")
    public Flux<ProductDto> getProductsBetweenPriceRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return productService.getProductsInPriceRange(min,max);
    }

    @PostMapping("/")
    public Mono<ProductDto> save(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<ProductDto> update(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id) {
        return productService.updateProduct(productDtoMono, id);
    }

    @DeleteMapping ("/{id}")
    public Mono<Void> update( @PathVariable String id) {
        return productService.deleteProduct( id);
    }
}

