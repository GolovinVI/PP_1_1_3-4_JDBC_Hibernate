package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        try {connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USER(id mediumint not null auto_increment, name VARCHAR(50), lastname VARCHAR(50), age tinyint, PRIMARY KEY (id))");
            System.out.println("The table has been created");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
        }

    }

    public void dropUsersTable() {
        Statement statement = null;
        try {connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS USER");
            System.out.println("Table deleted");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        try{connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO user(name, lastName, age) VALUES(?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {
                }
            }
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException ignored) {
                }
            }
        }

    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        try {connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e){
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {
                }
            }
        } finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException ignored) {
                }
            }
        }

    }

    public List<User> getAllUsers() {
        Statement statement = null;
        ResultSet resultSet;
        List<User> result = new ArrayList<>();
        try {connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user");
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                result.add(user);
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {
                }
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException ignored) {
            }
        }
        return result;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM user");
            System.out.println("The table is cleared");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to clear");
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException ignored) {
                }
            }
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
