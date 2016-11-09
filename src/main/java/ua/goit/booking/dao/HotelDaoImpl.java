package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.HotelDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.exception.DataCorruptionException;
import ua.goit.booking.exception.OperationFailException;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HotelDaoImpl extends AbstractDaoImp<Hotel> implements HotelDao {

    public HotelDaoImpl() {
        super(new File("static/hotels.json"), new TypeReference<List<Hotel>>() {
        });
    }

    @Override
    public Hotel save(Hotel hotel) throws AbstractDaoException {
        if (hotel == null) {
            throw new HotelDaoException("This hotel cannot be saved");
        }
        Hotel hotelInDB = findBy(hotel.getHotelName(), hotel.getCityName());
        if (hotelInDB != null && !hotelInDB.getId().equals(hotel.getId())) {
            throw new HotelDaoException("This hotel is already exist in DB");
        }
        return super.save(hotel);
    }

    @Override
    public boolean delete(Hotel hotel) {
//        if (hotel == null || !isContainId(hotel.getId())) {
//            return false;
//        }
        RoomDao roomDao = new RoomDaoImpl();
        hotel.getRooms().forEach(roomDao::delete);
        List<Hotel> allHotels = getAll();
//        if (isDataCorrupted(allHotels)) {
//            try {
//                throw new DataCorruptionException("WARNING! Data not available");
//            } catch (RuntimeException re) {
//                re.printStackTrace();
//            }
//        }
        Iterator<Hotel> iterator = allHotels.iterator();
        while (iterator.hasNext()) {
            Hotel element = iterator.next();
            if (element.getId().equals(hotel.getId())) {
                iterator.remove();
            }
        }
        return true;
    }

    /*@Override
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
    }*/

    @Override
    public Room addRoom(Hotel hotel, Room room) throws AbstractDaoException {
        if (room == null || hotel == null) {
            throw new HotelDaoException("This room cannot be saved to the hotel!");
        }
        List<Long> roomsId = hotel.getRoomsId();
        if (!roomsId.contains(room.getId())) {
            roomsId.add(room.getId());
//            hotel.setRoomsId(roomsId);
            save(hotel);
        }
        room.setHotelId(hotel.getId());
        RoomDao roomDao = new RoomDaoImpl();
        roomDao.save(room);

        return room;
    }

    public Hotel findBy(String hotelName, String city) throws HotelDaoException {
        if (hotelName == null
                || hotelName.equals("")
                || city == null
                || city.equals("")) {
            throw new HotelDaoException("Hotel cannot be with empty fields!");
        }
        List<Hotel> hotels = findHotelByCity(city).stream().filter(hotel -> hotel.getHotelName().equals(hotelName)).collect(Collectors.toList());
        if (hotels.isEmpty()) {
            return null;
        }
        return hotels.get(0);
    }

    private List<Hotel> findBy(String param, Predicate<Hotel> predicate) {
        if (param == null || param.equals("")) {
            return getAll();
        }
        return getAll().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Hotel> findHotelByName(String name) {
        return findBy(name, (hotel -> hotel.getHotelName().equals(name)));
    }

    @Override
    public List<Hotel> findHotelByCity(String city) {
        return findBy(city, (hotel -> hotel.getCityName().equals(city)));
    }
}