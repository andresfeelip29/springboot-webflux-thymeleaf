package com.co.course.webflux.spring.services;

import com.co.course.webflux.spring.repositories.dao.contracts.IProductRepository;
import com.co.course.webflux.spring.repositories.models.documents.Product;
import com.co.course.webflux.spring.services.contracts.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> getUserById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
