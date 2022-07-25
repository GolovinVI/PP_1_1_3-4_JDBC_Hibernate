package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        } return connection;
    }
//    public static void rollBackQuietly(Connection connection){
//        if (connection != null) {
//            try {
//                connection.rollback();
//                connection.setAutoCommit(true);
//            } catch (SQLException ignored) {
//            }
//        }
//    }
//    public static void closeQuietly(ResultSet resultSet){
//        if (resultSet!=null){
//            try {
//                resultSet.close();
//            } catch (SQLException ignored) {
//            }
//        }
//    }
//    public static void closeQuietly(Statement statement){
//        if (statement!=null){
//            try {
//                statement.close();
//            } catch (SQLException ignored) {
//            }
//        }
//    }
//    public static void closeQuietly(Connection connection){
//        if (connection!=null){
//            try {
//                connection.close();
//                connection.setAutoCommit(true);
//            } catch (SQLException ignored) {
//            }
//        }
//    }
//    public static void closeQuietly(PreparedStatement preparedStatement){
//        if (preparedStatement!=null){
//            try {
//                preparedStatement.close();
//            } catch (SQLException ignored) {
//            }
//        }
//    }






}
