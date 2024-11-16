package ru.kpfu.itis.kirillakhmetov.controller;

import ru.kpfu.itis.kirillakhmetov.dao.UserDao;
import ru.kpfu.itis.kirillakhmetov.dto.UserRegistrationDto;
import ru.kpfu.itis.kirillakhmetov.service.RegistrationService;
import ru.kpfu.itis.kirillakhmetov.util.DatabaseConnectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg")
public class RegistrationController extends HttpServlet {
    private RegistrationService registrationService;

    @Override
    public void init() throws ServletException {
        this.registrationService = (RegistrationService) getServletContext().getAttribute("registrationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registrationService.registerUser(new UserRegistrationDto(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("password")
        ));
    }
}
