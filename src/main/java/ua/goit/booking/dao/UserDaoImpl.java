package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.User;
import ua.goit.booking.exception.OperationFailException;

import java.io.File;
import java.util.List;

public class UserDaoImpl extends AbstractDaoImp<User> implements UserDao {

    public UserDaoImpl() {
        super(new File("static/users.json"), new TypeReference<List<User>>() {
        });
    }

    @Override
    public boolean isDataCorrupted(List<User> userList) {
        User user;
        if (userList == null) {
            return true;
        }
        if (userList.isEmpty()) {
            return true;
        }
        for (User anUserList : userList) {
            user = anUserList;
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

    @Override
    public boolean isLoggedIn(Long userId) {
        if (!isContainId(userId)) {
           return false;
        }
        return true;
    }
}
