package com.co.course.webflux.spring.services.contracts;

import com.co.course.webflux.spring.controllers.ProductController;
import com.co.course.webflux.spring.repositories.models.documents.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<Product> findAll();

    Mono<Product> getUserById(String id);

    Mono<Product> save(Product user);

    void delete(String id);

}
