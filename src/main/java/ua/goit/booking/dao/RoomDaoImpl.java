package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDaoImpl extends AbstractDaoImp<Room> implements RoomDao {

    public RoomDaoImpl() {
        super(new File("static/rooms.json"), new TypeReference<List<Room>>() {
        });
    }

    @Override
    public boolean delete(Room room) {
        if (room == null) {
            //TODO Exception
            return false;
        }
        HotelDao hotelDao = new HotelDaoImpl();
        Hotel hotel = hotelDao.getById(room.getHotelId());
        if (hotel.getRoomsId().contains(room.getId())) {
            List<Room> hotelRooms = hotel.getRooms().stream()
                    .filter(aRoom -> !aRoom.getId().equals(room.getId()))
                    .collect(Collectors.toList());
            hotel.setRooms(hotelRooms);
        }
        if (!isContainId(room.getId())) {
            return false;
        }
        List<Room> allRooms = getAll().stream()
                .filter(aRoom -> !aRoom.getId().equals(room.getId()))
                .collect(Collectors.toList());
        updateBase(allRooms);
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
            if (room.getToDate().after(room.getFromDate()) && room.getUserId() == null) {
                return true;
            }
        }
        return false;
    }


}
