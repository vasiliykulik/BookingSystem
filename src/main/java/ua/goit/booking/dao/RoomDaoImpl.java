package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.Room;

import java.io.File;
import java.util.List;

/**
 * Created by Dima on 05.11.16.
 */
public class RoomDaoImpl extends AbstractDaoImp<Room> implements IRoomDao {

    public RoomDaoImpl() {
        super(new File("static/rooms.json"), new TypeReference<List<Room>>() {
        });
    }
}
