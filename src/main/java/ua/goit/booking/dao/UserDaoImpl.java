package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.model.entity.User;

import java.io.File;
import java.util.List;

public class UserDaoImpl extends AbstractDaoImp<User> implements UserDao {

    public UserDaoImpl() {
        super(new File("db/users.json"), new TypeReference<List<User>>() {
        });
    }

}
