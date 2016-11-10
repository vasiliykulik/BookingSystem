package ua.goit.booking.controller;

import ua.goit.booking.controller.exception.HotelControllerException;
import ua.goit.booking.dao.HotelDao;
import ua.goit.booking.dao.HotelDaoImpl;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.HotelDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.util.List;

public class HotelController {
    private static HotelDao hotelDao = new HotelDaoImpl();

    public Hotel findBy(String hotelName, String city) throws HotelControllerException {
        try {
            return hotelDao.findBy(hotelName, city);
        } catch (HotelDaoException e) {
            throw new HotelControllerException(e.getMessage());
        }
    }

    public List<Hotel> findHotelByName(String name) {
        return hotelDao.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) {
        return hotelDao.findHotelByCity(city);
    }

    public Hotel save(Hotel hotel) throws HotelControllerException {
        HotelDao hotelDao = new HotelDaoImpl();
        try {
            return hotelDao.save(hotel);
        } catch (AbstractDaoException e) {
            throw new HotelControllerException(e.getMessage());
        }
    }

    public List<Hotel> getAll() {
        HotelDao hotelDao = new HotelDaoImpl();
        return hotelDao.getAll();
    }

    public void addRoom(Hotel hotel, Room room) throws HotelControllerException {
        HotelDao hotelDao = new HotelDaoImpl();
        try {
            hotelDao.addRoom(hotel, room);
        } catch (AbstractDaoException e) {
            throw new HotelControllerException(e.getMessage());
        }
    }

    public Hotel getById(Long id) throws HotelControllerException {
        HotelDao hotelDao = new HotelDaoImpl();
        try {
            return hotelDao.getById(id);
        } catch (AbstractDaoException e) {
            throw new HotelControllerException(e.getMessage());
        }
    }

    public boolean deleteById(long hotelId) {
        try {
            return hotelDao.deleteById(hotelId);
        } catch (AbstractDaoException e) {
            e.printStackTrace();
            return false;
        }
    }
}
