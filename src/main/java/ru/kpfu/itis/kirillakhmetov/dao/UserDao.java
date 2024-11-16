package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.User;
import ru.kpfu.itis.kirillakhmetov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.time.LocalDate;

public class UserDao {
    public void add(User user) {
        //language=sql
        String addUserSqlQuery = "INSERT INTO users (name, login, password) VALUES(?, ?, ?)";
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addUserSqlQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getByLogin(String login) {
        User user = null;
        //language=sql
        String addUserSqlQuery = "SELECT * FROM users WHERE login = ?";
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addUserSqlQuery);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    user = new User(
                            resultSet.getString("name"),
                            resultSet.getString("login"),
                            resultSet.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void addAttemptUser(String login, LocalDate date, Boolean status) {
        //language=sql
        String addUserSqlQuery = "INSERT INTO attempt (login, time, status) VALUES(?, ?, ?)";
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(addUserSqlQuery);
            preparedStatement.setString(1, login);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setBoolean(3, status);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
