package com.co.course.webflux.spring.repositories.dao.contracts;

import com.co.course.webflux.spring.repositories.models.documents.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}
