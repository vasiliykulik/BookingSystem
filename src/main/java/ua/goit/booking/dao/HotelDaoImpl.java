package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.HotelDaoException;
import ua.goit.booking.model.entity.Hotel;
import ua.goit.booking.model.entity.Room;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HotelDaoImpl extends AbstractDaoImp<Hotel> implements HotelDao {

    public HotelDaoImpl() {
        super(new File("db/hotels.json"), new TypeReference<List<Hotel>>() {
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
    public boolean delete(Hotel hotel) throws AbstractDaoException {
        RoomDao roomDao = new RoomDaoImpl();
        try {
            roomDao.delete(hotel.getRooms());
        } catch (AbstractDaoException e) {
            e.printStackTrace();
        }
        super.delete(hotel);
        return true;
    }

    @Override
    public Room addRoom(Hotel hotel, Room room) throws AbstractDaoException {
        if (room == null || hotel == null) {
            throw new HotelDaoException("This room cannot be saved to the hotel!");
        }
        List<Long> roomsId = hotel.getRoomsId();
        if (!roomsId.contains(room.getId())) {
            roomsId.add(room.getId());
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
        List<Hotel> hotels = getAll().stream()
                .filter(hotel -> hotel.getCityName().equals(city))
                .filter(hotel -> hotel.getHotelName().equals(hotelName))
                .collect(Collectors.toList());
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
    public List<Hotel> findHotelByName(String name) throws HotelDaoException {
        List<Hotel> result = findBy(name, (hotel -> hotel.getHotelName().equals(name)));
        if (result.size() == 0) {
            throw new HotelDaoException("Wrong hotel name!");
        }
        return result;
    }

    @Override
    public List<Hotel> findHotelByCity(String city) throws HotelDaoException {
        List<Hotel> result = findBy(city, (hotel -> hotel.getCityName().equals(city)));
        if (result.size() == 0) {
            throw new HotelDaoException("Wrong city!");
        }
        return result;
    }

}