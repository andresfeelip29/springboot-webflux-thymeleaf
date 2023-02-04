package com.co.course.webflux.spring.repositories.dao.contracts;

import com.co.course.webflux.spring.repositories.models.documents.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}
