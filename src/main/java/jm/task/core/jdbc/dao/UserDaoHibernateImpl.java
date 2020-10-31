package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.sql.Statement;
import java.util.List;
import java.util.Objects;


public class UserDaoHibernateImpl extends Util implements UserDao, UserService {


    public UserDaoHibernateImpl() {

    }

    //    @SuppressWarnings("unchecked")
    @Override
    public void createUsersTable() {
        try {
            Statement statement = Objects.requireNonNull(getConnection()).createStatement();
            statement.executeUpdate("CREATE TABLE dao (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastname VARCHAR(40), age INT)");
            System.out.println("Database has been created!");

        } catch (Throwable e) {
            System.err.println("Уже есть такая таблица");
        }

    }

    @Override
    public void dropUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("DROP TABLE dao");
            System.out.println("Database has been dropped!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory()) {
            session.beginTransaction();

            User user = new User();
            user.setId(0);
            user.setName("Dimitrii");
            user.setLastName("Rtutnev");
            user.setAge((byte) 23);

            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Util.shutdown();

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory()) {
            session.beginTransaction();
            User user = null;
            user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Util.shutdown();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = Util.getSessionFactory()) {
            session.beginTransaction();

            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;

    }


    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Util.shutdown();
    }

}

