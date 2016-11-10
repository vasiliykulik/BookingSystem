package ua.goit.booking.controller;

import ua.goit.booking.dao.*;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserController {

    private static UserDao userDao = new UserDaoImpl();


    public void save(User user) {
        try {
            userDao.save(user);
        } catch (AbstractDaoException e) {
            e.printStackTrace();
        }
    }
    // повертає масив усіх користувачів (без дублювання)
    // повертає null, якщо в базі немає користувачів
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        AbstractDao<User> userDao = new UserDaoImpl();
        List<User> allUsers = userDao.getAll();
        Set<User> userSet = new HashSet<>();

        try {
            userSet.addAll(allUsers);
            result.addAll(userSet);
            if (result.isEmpty()) {

                return null;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return result;
    }


    /*public User logIn(User user) {
        UserDao userDao = new UserDaoImpl();
        List<User> allUsers = userDao.getAll();
        User user1 = null;
        try {
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            user1 = userDao.save(user);
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return user1;
    }*/

    /*public boolean deleteUser(User user) {
        UserDao userDao = new UserDaoImpl();
        List<User> allUsers = userDao.getAll();
        boolean successFlag = false;
        try {
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            successFlag = userDao.deleteById(user);
            if (!successFlag) {
                try {
                    throw new OperationFailException("Can't deleteById " + user);
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return successFlag;
    }*/

}
