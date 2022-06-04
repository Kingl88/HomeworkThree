package ru.gb.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.webapp.DAO.ProductDAO;
import ru.gb.webapp.models.Product;
import ru.gb.webapp.repositories.ProductRepository;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {
    private ProductDAO dao;

    @Autowired
    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }
    public List<Product> findAll() throws SQLException {
        return dao.findAll();
    }
    public void deleteById(Long id) throws SQLException {
        dao.deleteById(id);
    }

    //    private ProductRepository repository;
//
//    @Autowired
//    public ProductService(ProductRepository repository) {
//        this.repository = repository;
//    }
//
//    public List<Product> findAll() {
//        return repository.findAll();
//    }
//
//    public void addProduct(Product product) {
//        repository.addProduct(product);
//    }
//
    public Product getById(Long id) throws SQLException {
        return dao.findById(id);
    }

    public void costUp(Long id) throws SQLException {
       Product product = dao.findById(id);
       product.setCost(dao.findById(id).getCost() + 1);
       dao.saveOrUpdate(product);
    }

    public void costDown(Long id) throws SQLException {
        if(dao.findById(id).getCost() > 0) {
            Product product = dao.findById(id);
            product.setCost(dao.findById(id).getCost() - 1);
            dao.saveOrUpdate(product);
        }
    }
    public Product saveOrUpdate(Product product) throws SQLException {
        return dao.saveOrUpdate(product);
    }
}
