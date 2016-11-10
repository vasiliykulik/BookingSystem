package ua.goit.booking.controller;

import ua.goit.booking.controller.exception.RoomControllerException;
import ua.goit.booking.dao.RoomDao;
import ua.goit.booking.dao.RoomDaoImpl;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.entity.Room;

import java.util.List;
import java.util.Map;

public class RoomController {

    private static RoomDao roomDao = new RoomDaoImpl();

    public List<Room> getAll() {
        return roomDao.getAll();
    }

    public Room save(Room room) throws RoomControllerException {
        try {
            return roomDao.save(room);
        } catch (AbstractDaoException e) {
            throw new RoomControllerException(e.getMessage());
        }
    }

    public List<Room> findRooms(Map<String, String> params) {
        return roomDao.findRoom(params);
    }

    public boolean deleteById(long id) {
        try {
            return roomDao.deleteById(id);
        } catch (AbstractDaoException e) {
            e.printStackTrace();
            return false;
        }
    }

}
