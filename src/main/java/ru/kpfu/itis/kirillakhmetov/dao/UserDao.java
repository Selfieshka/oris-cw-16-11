package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.entity.User;
import ru.kpfu.itis.kirillakhmetov.util.DatabaseConnectionUtil;

import java.sql.*;
import java.time.LocalDate;

public class UserDao {
    //language=sql
    private static final String SQL_SAVE = "INSERT INTO users (name, login, password) VALUES(?, ?, ?)";
    private static final String SQL_GET_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_ADD_ATTEMPT = "INSERT INTO attempt (login, time, status) VALUES(?, ?, ?)";

    public void add(User user) {
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
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
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_LOGIN);
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

    public void saveAttempt(String login, LocalDate date, Boolean status) {
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ATTEMPT);
            preparedStatement.setString(1, login);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setBoolean(3, status);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
