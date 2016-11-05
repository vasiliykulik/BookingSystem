package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.User;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dima on 05.11.16.
 */
public class UserDAO extends DAOImp<User> {

    public UserDAO() {
        super(new File("static/users.json"), new TypeReference<ArrayList<User>>() {
        });
    }
}
