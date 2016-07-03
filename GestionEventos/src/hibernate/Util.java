package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by JuanAJ on 15/06/2016.
 */
public class Util {
    private static SessionFactory sessionFactory;
    public static Session session;

    public static void buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
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
