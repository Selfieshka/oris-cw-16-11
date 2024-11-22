package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.WeatherDao;
import ru.kpfu.itis.kirillakhmetov.dto.WeatherRecordAttempt;
import ru.kpfu.itis.kirillakhmetov.util.HttpClient;
import ru.kpfu.itis.kirillakhmetov.util.HttpClientImpl;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherService {
    private static String STRING = "ec66597bce4293a00deff87f0380b603";
    private static String URL = "http://api.openweathermap.org/data/2.5/weather";
    private final WeatherDao weatherDao;

    public WeatherService(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public Double checkWeather(String city) {
        HttpClient httpClient = new HttpClientImpl();
        StringBuilder sb = new StringBuilder().append(URL)
                .append("?q=")
                .append(city)
                .append("&appid=")
                .append(STRING)
                .append("&units=metric");

        String result = httpClient.get(sb.toString(), Collections.emptyMap(), Collections.emptyMap());
        String regex = "\"temp\":(\\d+\\.\\d+|\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }

        return 0D;
    }

    public void saveWeatherAttempt(WeatherRecordAttempt weatherRecordAttempt) {
        weatherDao.save(weatherRecordAttempt);
    }
}
