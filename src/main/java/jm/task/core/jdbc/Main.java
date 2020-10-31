package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserService userService = new UserDaoHibernateImpl();
//        userService.createUsersTable();
//        userService.saveUser("2asd", "sad2", (byte) 4);
//        userService.removeUserById(3);
//        userService.getAllUsers();
        userService.createUsersTable();


    }
}
