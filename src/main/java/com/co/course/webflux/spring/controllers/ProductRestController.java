package com.co.course.webflux.spring.controllers;

import com.co.course.webflux.spring.repositories.models.documents.Product;
import com.co.course.webflux.spring.services.contracts.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/Product")
public class ProductRestController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private IProductService productService;

    @GetMapping()
    public Flux<Product> index() {
        Flux<Product> productFlux = productService.findAll().map(product -> {
                    product.setName(product.getName().toUpperCase());
                    return product;
                })
                .doOnNext(product -> log.info(product.getName()));
        return productFlux;
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id){
       // return productService.getUserById(id);

        Flux<Product> productFlux = productService.findAll();

        Mono<Product> monoProduct = productFlux.
                filter(product -> product.getId().equals(id))
                .next()
                .doOnNext(product -> log.info(product.getName()));

        return monoProduct;
    }

}
