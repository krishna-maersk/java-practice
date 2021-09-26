package com.example.learnspringreactive.repository;

import com.example.learnspringreactive.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {
    Flux<Product> findByPriceBetween(Range<Double> priceRange);
}
