package ua.goit.booking.dao;

import ua.goit.booking.entity.Hotel;
import java.util.List;
import java.util.Map;

/**
 * Created by taras on 04.11.16.
 */
public interface HotelDAO {

    List<Hotel> getAllHotels();

    void update(List<Hotel> hotels);
}
