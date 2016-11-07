package ua.goit.booking.dao;

import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

public interface HotelDao extends AbstractDao<Hotel> {

    Room addRoom(Hotel hotel, Room room);

}
