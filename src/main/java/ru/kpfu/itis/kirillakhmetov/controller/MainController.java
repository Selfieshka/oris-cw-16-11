package ru.kpfu.itis.kirillakhmetov.controller;

import ru.kpfu.itis.kirillakhmetov.dto.UserAuthorizationDao;
import ru.kpfu.itis.kirillakhmetov.service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class MainController extends HttpServlet {
    private AuthorizationService authorizationService;

    @Override
    public void init() throws ServletException {
        authorizationService = (AuthorizationService) getServletContext().getAttribute("authorizationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (authorizationService.authorizeUser(new UserAuthorizationDao(
                        req.getParameter("login"),
                        req.getParameter("password")
                ),
                resp)) {
            resp.sendRedirect(req.getContextPath() + "/user");
        } else {
            resp.sendRedirect(req.getContextPath() + "/index");
        }
        ;
    }
}
