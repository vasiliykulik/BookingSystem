package ua.goit.booking.controller;

import ua.goit.booking.dao.UserDao;
import ua.goit.booking.dao.UserDaoImpl;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.entity.User;

import java.util.List;

public class UserController {

    private static UserDao userDao = new UserDaoImpl();

    public void save(User user) {
        try {
            userDao.save(user);
        } catch (AbstractDaoException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

}
