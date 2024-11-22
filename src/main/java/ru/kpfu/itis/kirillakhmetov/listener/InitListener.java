package ru.kpfu.itis.kirillakhmetov.listener;

import ru.kpfu.itis.kirillakhmetov.dao.UserDao;
import ru.kpfu.itis.kirillakhmetov.dao.WeatherDao;
import ru.kpfu.itis.kirillakhmetov.service.AuthorizationService;
import ru.kpfu.itis.kirillakhmetov.service.RegistrationService;
import ru.kpfu.itis.kirillakhmetov.service.WeatherService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDao userDao = new UserDao();
        WeatherDao weatherDao = new WeatherDao();
        sce.getServletContext().setAttribute("authorizationService", new AuthorizationService(userDao));
        sce.getServletContext().setAttribute("registrationService", new RegistrationService(userDao));
        sce.getServletContext().setAttribute("weatherService", new WeatherService(weatherDao));
    }
}
