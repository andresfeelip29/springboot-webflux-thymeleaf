package com.co.course.webflux.spring.controllers;


import com.co.course.webflux.spring.repositories.models.documents.Product;
import com.co.course.webflux.spring.services.contracts.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private IProductService productService;

    @GetMapping({"/Products", "/"})
    public String listProducts(Model model) {
        Flux<Product> productFlux = productService.findAll().map(product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        });

        productFlux.subscribe(product -> log.info(product.getName()));

        model.addAttribute("products", productFlux);
        model.addAttribute("title", "List of Products");
        return "list-products";
    }

    @GetMapping("/FullProducts")
    public String listFullProducts(Model model) {
        Flux<Product> productFlux = productService.findAll().map(product -> {
                    product.setName(product.getName().toUpperCase());
                    return product;
                })
                .repeat(500);

        productFlux.subscribe(product -> log.info(product.getName()));

        model.addAttribute("products", productFlux);
        model.addAttribute("title", "List of Products");
        return "list-products";
    }

    @GetMapping("/ProductsDataDriver")
    public String listDataDriverProducts(Model model) {
        Flux<Product> productFlux = productService.findAll().map(product -> {
                    product.setName(product.getName().toUpperCase());
                    return product;
                })
                .delayElements(Duration.ofSeconds(1));

        productFlux.subscribe(product -> log.info(product.getName()));

        model.addAttribute("products", new ReactiveDataDriverContextVariable(productFlux, 2));
        model.addAttribute("title", "List of Products");
        return "list-products";
    }
}
