package ru.gb.webapp.DAO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.webapp.models.Product;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDAO implements AbstractDAO<Product> {
    private SessionFactoryWrapper sessionFactoryWrapper;

    @Autowired
    public void setSessionFactoryWrapper(SessionFactoryWrapper sessionFactoryWrapper) {
        this.sessionFactoryWrapper = sessionFactoryWrapper;
    }

    private Session session;

    @PostConstruct
    public void init() {
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session = sessionFactoryWrapper.getFactory().getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findById(Long id) throws SQLException {
        session = sessionFactoryWrapper.getFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        session = sessionFactoryWrapper.getFactory().getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product order by id").getResultList();
        session.getTransaction().commit();
        return products;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        session = sessionFactoryWrapper.getFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
    }

    @Override
    public Product saveOrUpdate(Product product) throws SQLException {
        session = sessionFactoryWrapper.getFactory().getCurrentSession();
        session.beginTransaction();
        Product temp = session.get(Product.class, product.getId());
        if(temp != null) {
            temp.setId(product.getId());
            temp.setCost(product.getCost());
            temp.setTitle(product.getTitle());
        } else {
            session.save(product);
        }
        session.getTransaction().commit();
        return temp;
    }
}
