package ru.kpfu.itis.kirillakhmetov.listener;

import ru.kpfu.itis.kirillakhmetov.dao.UserDao;
import ru.kpfu.itis.kirillakhmetov.service.AuthorizationService;
import ru.kpfu.itis.kirillakhmetov.service.RegistrationService;
import ru.kpfu.itis.kirillakhmetov.util.DatabaseConnectionUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("authorizationService", new AuthorizationService());
        sce.getServletContext().setAttribute("registrationService", new RegistrationService());
    }
}
