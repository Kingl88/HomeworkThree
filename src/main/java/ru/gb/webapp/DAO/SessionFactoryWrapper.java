package ru.gb.webapp.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryWrapper {
    private SessionFactory factory;
    public SessionFactory getFactory() {
        if (factory == null) {
            factory = new Configuration()
                    .configure("configs/hibernate.cfg.xml")
                    .buildSessionFactory();
        }
        return factory;
    }
}
