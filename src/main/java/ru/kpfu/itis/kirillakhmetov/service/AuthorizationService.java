package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.UserDao;
import ru.kpfu.itis.kirillakhmetov.dto.UserAuthorizationDao;
import ru.kpfu.itis.kirillakhmetov.entity.User;
import ru.kpfu.itis.kirillakhmetov.util.PasswordUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class AuthorizationService {

    private final UserDao userDao;

    public AuthorizationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean authorizeUser(UserAuthorizationDao user, HttpServletResponse resp) throws IOException {
        User userFromDb = userDao.getByLogin(user.login());
        if (userFromDb != null) {
            if (user.login().equals(userFromDb.getLogin()) &&
                    PasswordUtil.encrypt(user.password()).equals(userFromDb.getPassword())) {
                Cookie cookie = new Cookie("user", user.login());
                cookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(cookie);
            } else {
                resp.sendRedirect("/login");
            }
            userDao.saveAttempt(user.login(), LocalDate.now(), true);
            return true;
        } else {
            userDao.saveAttempt(user.login(), LocalDate.now(), false);
            return false;
        }
    }
}
