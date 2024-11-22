package ru.kpfu.itis.kirillakhmetov.dao;

import ru.kpfu.itis.kirillakhmetov.dto.WeatherRecordAttempt;
import ru.kpfu.itis.kirillakhmetov.util.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherDao {
    //language=sql
    private static final String SQL_ADD = "INSERT INTO weather (user_login, city) VALUES(?, ?)";

    public void save(WeatherRecordAttempt weather) {
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD);
            preparedStatement.setString(1, weather.user_login());
            preparedStatement.setString(2, weather.city());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
