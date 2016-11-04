package ua.goit.booking.dao;

import ua.goit.booking.entity.hotel.Hotel;
import java.util.List;
import java.util.Map;

/**
 * Created by taras on 04.11.16.
 */
public interface AbstractDAO {

    List<Hotel> findHotelByName(String name);
    List<Hotel> findHotelDyCity(String city);
    void bookRoom(long roomId, long userId, long hotelId);
    void cancelReservation(long roomId, long userId, long hotelId);
    List<Hotel> findRoom(Map<String, String> params);

}
