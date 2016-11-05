package ua.goit.booking.dao;

import ua.goit.booking.entity.Room;

import java.io.File;

/**
 * Created by Dima on 05.11.16.
 */
public class RoomDAO extends DAOImp<Room> {

    public RoomDAO() {
        super(new File("static/rooms.json"));
    }
}
