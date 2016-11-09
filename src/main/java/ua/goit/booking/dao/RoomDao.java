package ua.goit.booking.dao;

import ua.goit.booking.entity.Room;

import java.util.List;
import java.util.Map;

public interface RoomDao extends AbstractDao<Room> {

    List<Room> findRoom(Map<String, String> params);

}
