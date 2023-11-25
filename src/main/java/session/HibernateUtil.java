package session;

import model.Employee;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.util.Properties;

public class HibernateUtil {


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
//this is a singleton pattern for the session factory so that every session
        //it can generate multiple sessions
        if (sessionFactory == null) {
//this is like a callable statement?1
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/email_generator");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "password");


                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
               //changed to update so that it will not re-create the table per entry
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Employee.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();


                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return sessionFactory;
    }


}
