package ru.gb.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.webapp.models.Product;
import ru.gb.webapp.services.ProductService;

import java.sql.SQLException;

@Controller
public class ProductsController {

    private ProductService service;

    @Autowired
    public ProductsController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/show_all")
    public String showAllProducts(Model model) throws SQLException {
        model.addAttribute("products", service.findAll());
        return "products";
    }
    @GetMapping(value = "/show/{id}")
    public String showProductById(Model model,@PathVariable Long id) throws SQLException {
        model.addAttribute("product", service.getById(id));
        return "product_info";
    }
    @GetMapping(value = "/create")
    public String createProduct(){
        return "create_product";
    }

    @PostMapping(value = "/create")
    public String addProduct(@RequestParam Long id, @RequestParam String title, @RequestParam Double cost) throws SQLException {
        service.saveOrUpdate(new Product(id, title, cost));
        return "redirect:/show_all";
    }

    @GetMapping(value = "/costUp/{id}")
    public String costUpProduct(@PathVariable Long id) throws SQLException {
        service.costUp(id);
        return "redirect:/show_all";
    }

    @GetMapping(value = "/costDown/{id}")
    public String costDownProduct(@PathVariable Long id) throws SQLException {
        service.costDown(id);
        return "redirect:/show_all";
    }

    @GetMapping(value = "/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) throws SQLException {
        service.deleteById(id);
        return "redirect:/show_all";
    }
}
