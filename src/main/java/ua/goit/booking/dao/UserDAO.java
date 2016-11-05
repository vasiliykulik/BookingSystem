package ua.goit.booking.dao;

import ua.goit.booking.entity.User;

import java.io.File;

/**
 * Created by Dima on 05.11.16.
 */
public class UserDAO extends DAOImp<User> {

    public UserDAO() {
        super(new File("static/users.json"));
    }
}
