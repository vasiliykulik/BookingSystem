package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.User;

import java.io.File;
import java.util.List;

public class UserDaoImpl extends AbstractDaoImp<User> implements UserDao {

    public UserDaoImpl() {
        super(new File("static/users.json"), new TypeReference<List<User>>() {
        });
    }

}
