package ua.goit.booking.dao;

import ua.goit.booking.entity.Room;

import java.io.File;

public class RoomDAO extends DAOImp<Room> {

    public RoomDAO() {
        super(new File("static/rooms.json"));
    }
}
