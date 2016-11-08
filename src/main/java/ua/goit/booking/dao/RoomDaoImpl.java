package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
        try {
            hotelDao.addRoom(hotel, room);
        } catch (Exception e) {
            //TODO Catching Exception from HotelDao
            return null;
        }

        List<Room> rooms = getAll();

        if (isContainId(room.getId())) {
            Iterator<Room> iterator = rooms.iterator();
            Room aRoom = iterator.next();
            if (aRoom.getId() == room.getId()) {
                int index = rooms.indexOf(aRoom);
                rooms.set(index, room);
            }
        } else {
            rooms.add(room);
        }

        update(rooms);
        return room;
    }

    @Override
    public boolean delete(Room room) {
        if (room == null) {
            //TODO Exception
            return false;
        }
        HotelDao hotelDao = new HotelDaoImpl();
        Hotel hotel = hotelDao.getById(room.getHotelId());
        if (!hotel.getRoomsId().contains(room.getId())) {
            return false;
        }
        List<Room> hotelRooms = hotel.getRooms().stream()
                .filter(r -> !r.getId().equals(room.getId()))
                .collect(Collectors.toList());
        hotel.setRooms(hotelRooms);
        return true;
    }

    @Override
    public boolean isDataCorrupted(List<Room> roomList) {
        Room room;
        if (roomList == null) {
            return true;
        }
        if (roomList.isEmpty()) {
            return true;
        }
        for (Room aRoomList : roomList) {
            room = aRoomList;
            if (room == null) {
                return true;
            }
            if (room.getId() == null
                    || room.getPrice() == 0
                    || room.getNumberOfVisitors() == 0
                    || room.getHotelId() == null
                    || room.getFromDate() == null
                    || room.getToDate() == null) {
                return true;
            }
            if (!room.getFromDate().before(room.getToDate())) {
                return true;
            }
            if (room.isBooked() && room.getUserId() == null) {
                return true;
            }
        }
        return false;
    }


}
