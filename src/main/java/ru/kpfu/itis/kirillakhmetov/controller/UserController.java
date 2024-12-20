package ru.kpfu.itis.kirillakhmetov.controller;

import ru.kpfu.itis.kirillakhmetov.dto.WeatherRecordAttempt;
import ru.kpfu.itis.kirillakhmetov.service.WeatherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private WeatherService weatherService;

    @Override
    public void init() throws ServletException {
        this.weatherService = (WeatherService) getServletContext().getAttribute("weatherService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        Double temp = weatherService.checkWeather(city);
        getServletContext().setAttribute("temp", temp);
        getServletContext().setAttribute("city", city);
        String loginUser = Arrays.stream(req.getCookies()).filter(cookies -> cookies.getName() != null).findFirst().get().getValue();
        weatherService.saveWeatherAttempt(new WeatherRecordAttempt(
                loginUser,
                city
        ));
        resp.getWriter().write("Температура: %s".formatted(temp));
//        getServletContext().getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
    }
}