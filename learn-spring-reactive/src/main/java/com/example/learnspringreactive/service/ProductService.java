package com.example.learnspringreactive.service;

import com.example.learnspringreactive.dto.ProductDto;
import com.example.learnspringreactive.repository.ProductRepository;
import com.example.learnspringreactive.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts(){
        return productRepository
                .findAll()
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(String id){
        return productRepository
                .findById(id)
                .map(AppUtils::entityToDto);
    }


    public Flux<ProductDto> getProductsInPriceRange(double min, double max){
        return productRepository
                .findByPriceBetween(Range.closed(min,max))
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono
                .map(AppUtils::dtoTOEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::dtoTOEntity))
                .doOnNext( e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}


