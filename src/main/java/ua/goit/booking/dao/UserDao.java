package ua.goit.booking.dao;

import ua.goit.booking.entity.User;

public interface UserDao extends AbstractDao<User> {
    boolean isLoggedIn(Long userId);
}
