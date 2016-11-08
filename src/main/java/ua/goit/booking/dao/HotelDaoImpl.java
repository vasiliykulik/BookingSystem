package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class HotelDaoImpl extends AbstractDaoImp<Hotel> implements HotelDao {

    public HotelDaoImpl() {
        super(new File("static/hotels.json"), new TypeReference<List<Hotel>>() {
        });
    }

    @Override
    public boolean delete(Hotel hotel) {
        if (hotel == null || !isContainId(hotel.getId())) {
            return false;
        }
        RoomDao roomDao = new RoomDaoImpl();
        for (Room room : hotel.getRooms()) {
            roomDao.delete(room);
        }
        List<Hotel> allHotels = getAll();
        Iterator<Hotel> iterator = allHotels.iterator();
        while (iterator.hasNext()) {
            Hotel element = iterator.next();
            if (element.getId().equals(hotel.getId())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public boolean isDataCorrupted(List<Hotel> hotelList) {
        Hotel hotel;
        List<Long> roomsIds;
        if (hotelList == null) {
            return true;
        }
        if (hotelList.isEmpty()) {
            return true;
        }
        for (Hotel aHotelList : hotelList) {
            hotel = aHotelList;
            if (hotel == null) {
                return true;
            }
            roomsIds = hotel.getRoomsId();
            if (hotel.getId() == null || hotel.getHotelName() == null
                    || hotel.getCityName() == null || roomsIds == null) {
                return true;
            }
            for (Long roomsId : roomsIds) {
                if (roomsId == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Room addRoom(Hotel hotel, Room room) {
        if (room == null
                || hotel == null
                || room.getHotelId() != hotel.getId()) {
            System.out.println("This room cannot be saved!");
            //TODO Exception
            return null;
        }
        List<Long> roomsId = hotel.getRoomsId();
        if (roomsId.contains(room.getId())) {
            RoomDao roomDao = new RoomDaoImpl();
            roomDao.save(room);
            return room;
        }
        roomsId.add(room.getId());
        hotel.setRoomsId(roomsId);
        update(hotel);

        return room;
    }
}