package ru.gb.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.webapp.models.Product;
import ru.gb.webapp.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
public List<Product> findAll(){
        return repository.findAll();
}

    public void addProduct(Product product){
        repository.addProduct(product);
    }

    public Product getById(Long id){
       return repository.getById(id);
    }
}
