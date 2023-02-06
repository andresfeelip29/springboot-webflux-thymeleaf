package com.co.course.webflux.spring.repositories.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    @Setter @Getter
    private String id;

    @Setter @Getter
    private String name;

    @Setter @Getter
    private Double price;

    @Setter @Getter
    private LocalDate createAt;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
