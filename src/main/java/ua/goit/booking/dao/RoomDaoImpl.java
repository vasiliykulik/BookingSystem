package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dima on 05.11.16.
 */
public class RoomDaoImpl extends AbstractDaoImp<Room> implements RoomDao {

    public RoomDaoImpl() {
        super(new File("static/rooms.json"), new TypeReference<List<Room>>() {
        });
    }

    @Override
    public Room save(Room room) {
        if (room == null) {
            System.out.println("This room cannot be saved!");
            return null;
        }

        HotelDao hotelDao = new HotelDaoImpl();
        Hotel hotel = hotelDao.getById(room.getHotelId());

        if (hotel.getRoomsId().contains(room.getId())) {
            //TODO Logic, check current Logic
            System.out.println("This room is already exists!");
            return room;
        }

        hotel.addRoom(room);

        List<Room> rooms = getAll();
        rooms.add(room);

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("static/rooms.json"), rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public void delete(Room room) {
        //TODO Logic
    }
}
