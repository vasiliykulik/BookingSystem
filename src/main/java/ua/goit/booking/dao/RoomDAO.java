package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.Room;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dima on 05.11.16.
 */
public class RoomDAO extends DAOImp<Room> {

    public RoomDAO() {
        super(new File("static/rooms.json"), new TypeReference<ArrayList<Room>>() {
        });
    }
}
