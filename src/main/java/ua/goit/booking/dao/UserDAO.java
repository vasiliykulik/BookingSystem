package ua.goit.booking.dao;

import ua.goit.booking.entity.User;

import java.io.File;

public class UserDAO extends DAOImp<User> {
    public UserDAO() {
        super(new File("static/users.json"));
    }
}
