package ru.gb.webapp.repositories;

import org.springframework.stereotype.Component;
import ru.gb.webapp.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init(){
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Milk", 10.0),
                new Product(2L, "Tomato", 9.0),
                new Product(3L, "Bread", 3.5),
                new Product(4L, "Cheese", 12.8),
                new Product(5L, "Water", 2.0)
        ));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public Product getById(Long id){
        return products.stream().filter(s -> s.getId().equals(id)).findFirst().get();
    }
}
