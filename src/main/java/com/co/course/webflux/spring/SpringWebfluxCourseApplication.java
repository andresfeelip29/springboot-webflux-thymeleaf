package com.co.course.webflux.spring;

import com.co.course.webflux.spring.repositories.dao.contracts.IProductRepository;
import com.co.course.webflux.spring.repositories.models.documents.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@SpringBootApplication
public class SpringWebfluxCourseApplication implements CommandLineRunner {


    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    private static final Logger log = LoggerFactory.getLogger(SpringWebfluxCourseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxCourseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        mongoTemplate.dropCollection("products").subscribe();

        Flux.just(
                        new Product("TV Samnsum Led", 5000.0),
                        new Product("Camere GO PRO x400", 1250.0),
                        new Product("Apple Iphone X13x", 2500.0),
                        new Product("HP PAVILION z435", 2000.0),
                        new Product("TV Sony max 300", 9000.0),
                        new Product("Xbox X", 12000.0),
                        new Product("Nvidia RTX 3090", 2300.0),
                        new Product("Samnsun S21", 1500.0),
                        new Product("Mouse Steel series g342", 120.0),
                        new Product("Headshet Logitech g543", 200.0)
                ).flatMap(product -> {
                    product.setCreateAt(LocalDate.now());
                    return productRepository.save(product);
                })
                .subscribe(product -> log.info("Insert: " + product.getId() + " " + product.getName()));
    }
}
