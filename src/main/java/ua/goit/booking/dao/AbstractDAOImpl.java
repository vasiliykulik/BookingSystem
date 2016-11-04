package ua.goit.booking.dao;

import ua.goit.booking.entity.hotel.Hotel;

import java.util.List;
import java.util.Map;

/**
 * Created by taras on 04.11.16.
 */
public class AbstractDAOImpl implements AbstractDAO {

    public List<Hotel> findHotelByName(String name) {
        return null;
    }

    public List<Hotel> findHotelDyCity(String city) {
        return null;
    }

    public void bookRoom(long roomId, long userId, long hotelId) {

    }

    public void cancelReservation(long roomId, long userId, long hotelId) {

    }

    public List<Hotel> findRoom(Map<String, String> params) {
        return null;
    }
}
