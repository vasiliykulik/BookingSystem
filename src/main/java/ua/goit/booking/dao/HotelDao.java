package ua.goit.booking.dao;

import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.HotelDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.util.List;

public interface HotelDao extends AbstractDao<Hotel> {

    Room addRoom(Hotel hotel, Room room) throws AbstractDaoException;

    Hotel findBy(String hotelName, String city) throws HotelDaoException;

    List<Hotel> findHotelByCity(String city) throws HotelDaoException;

    List<Hotel> findHotelByName(String name) throws HotelDaoException;

}
