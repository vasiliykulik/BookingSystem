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

    @Override
    public User save(User user) {
        //TODO Logic
        return null;
    }

    @Override
    public void delete(User user) {
        //TODO Logic
    }

    @Override
    public boolean isDataCorrupted(List<User> userList) {
        User user;
        for (int i = 0; i < userList.size(); i++) {
            user = userList.get(i);
            if (user == null) {
                return true;
            }
            if (user.getId() == null || user.getFirstName() == null
                    || user.getLastName() == null || user.getEmailAddress() == null) {
                return true;
            }
        }
        return false;
    }
}
