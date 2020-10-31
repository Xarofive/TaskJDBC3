package jm.task.core.jdbc.util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.*;

public class Util {
    public static Connection getConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/foo?serverTimezone=Europe/Moscow&useSSL=false",
                    "root",
                    "4815162342v");
        } catch (Exception e) {
            System.err.println("Не удалось загрузить класс драйвера");
        }
        return null;
    }

    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSessionFactory() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }


}

