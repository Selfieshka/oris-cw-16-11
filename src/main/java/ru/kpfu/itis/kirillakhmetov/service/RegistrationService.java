package ru.kpfu.itis.kirillakhmetov.service;

import ru.kpfu.itis.kirillakhmetov.dao.UserDao;
import ru.kpfu.itis.kirillakhmetov.dto.UserRegistrationDto;
import ru.kpfu.itis.kirillakhmetov.entity.User;
import ru.kpfu.itis.kirillakhmetov.util.PasswordUtil;

public class RegistrationService {
    private final UserDao userDao = new UserDao();

    public void registerUser(UserRegistrationDto user) {
        userDao.add(new User(
                user.name(),
                user.login(),
                PasswordUtil.encrypt(user.password())
        ));
    }
}
