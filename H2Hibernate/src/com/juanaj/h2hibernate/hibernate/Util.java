package com.juanaj.h2hibernate.hibernate;

import com.juanaj.h2hibernate.entity.Usuarios;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {
    private static SessionFactory sessionFactory;
    public static Session session;

    public static void buildSessionFactory() {
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        try {
//            sessionFactory = new Configuration()
//                    .configure() // configures settings from hibernate.cfg.xml
//                    .addAnnotatedClass(Usuarios.class)
//                    .buildSessionFactory();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
        try {
            StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .configure() // configures settings from hibernate.cfg.xml
                            .build();
            Metadata metaData =
                    new MetadataSources(registry).getMetadataBuilder().build();
            sessionFactory = metaData
                    .getSessionFactoryBuilder()
                    .build();
        } catch (Throwable th) {
            System.err.println("Enitial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
    }

    public static void openSession() {

        session = sessionFactory.openSession();
    }

    public static Session getCurrentSession() {

        if (!session.isOpen())
            openSession();

        return session;
    }

    public static void closeSessionFactory() {

        if (session != null)
            session.close();

        if (sessionFactory != null)
            sessionFactory.close();

    }

}
