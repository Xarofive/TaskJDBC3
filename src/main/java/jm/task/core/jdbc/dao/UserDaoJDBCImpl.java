package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Util util = new Util();

    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE dao (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastname VARCHAR(40), age INT)");
            System.out.println("Database has been created!");

        } catch (Exception e) {
            System.err.println("Уже есть такая таблица");
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("DROP TABLE dao");
            System.out.println("Database has been dropped!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
//            Statement statement = util.getConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from dao");
//            statement = getConnection().createStatement();
            PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO dao (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();

            System.out.println("user saved");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = util.getConnection().prepareStatement("DELETE FROM dao WHERE ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

            System.out.println("user deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<User>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = util.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM dao");
            while (resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
                System.out.println(list.get(0));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }return list;



    }

    public void cleanUsersTable() {
        try {
            Statement statement = getConnection().createStatement();
            statement.execute("TRUNCATE TABLE dao");
            System.out.println("Database has been clean!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
