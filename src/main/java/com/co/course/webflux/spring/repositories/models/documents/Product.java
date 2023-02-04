package com.co.course.webflux.spring.repositories.models.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collation = "products")
public class Product {

    @Id
    private String id;

    @Setter @Getter
    private String name;

    @Setter @Getter
    private Double price;

    @Setter @Getter
    private LocalDate createAt;

}
