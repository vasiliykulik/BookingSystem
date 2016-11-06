package ua.goit.booking.controller;

import ua.goit.booking.dao.AbstractDao;
import ua.goit.booking.dao.HotelDaoImpl;
import ua.goit.booking.dao.RoomDaoImpl;
import ua.goit.booking.dao.UserDaoImpl;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserController {
    // повертає масив усіх користувачів (без дублювання)
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        AbstractDao<User> userDao = new UserDaoImpl();
        Set<User> userSet = new HashSet<>();
        userSet.addAll(userDao.getAll());
        result.addAll(userSet);
        return result;
    }

    // за даним id готелю (hotelId) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    public List<User> getAllUsersFromHotel(long hotelId) {
        List<User> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        Set<User> userSet = new HashSet<>();
        Hotel hotel;

        hotels.addAll(hotelDao.getAll());
        hotel = hotelDao.getById(hotelId);
        if (!hotels.contains(hotel)) {
            //  System.out.println("There's no hotel with such ID in DB!");
            return null;
        }
        rooms.addAll(hotel.getRooms());
        userSet.addAll(rooms.stream().map(Room::getUser).collect(Collectors.toList()));
        result.addAll(userSet);
        return result;
    }

    // за даною назвою готелю (hotelName) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    public List<User> getAllUsersFromHotel(String hotelName) {
        List<User> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        Set<User> userSet = new HashSet<>();
        Hotel ourHotel = null;

        hotels.addAll(hotelDao.getAll());
        for (Hotel hotelTmp : hotels) {
            if (hotelTmp.getHotelName().equals(hotelName)) {
                ourHotel = hotelTmp;
                break;
            }
        }
        if (ourHotel == null) {
            //  System.out.println("There's no hotel with such name in DB!");
            return null;
        }
        rooms.addAll(ourHotel.getRooms());
        userSet.addAll(rooms.stream().map(Room::getUser).collect(Collectors.toList()));
        result.addAll(userSet);
        return result;
    }

    // за даною назвою міста (theCity) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    public List<User> getAllUsersFromCity(String theCity) {
        List<User> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<Long> roomsIds = new ArrayList<>();
        List<Long> usersIds = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        Set<User> userSet = new HashSet<>();

        hotels.addAll(hotelDao.getAll().stream()
                .filter(hotel -> (hotel.getCityName().equals(theCity))).collect(Collectors.toList()));
        if (hotels.isEmpty()) {
            //  System.out.println("There's no such city in DB!");
            return null;
        }
        for (Hotel hotel : hotels) {
            roomsIds.addAll(hotel.getRoomsId());
        }
        rooms.addAll(roomDao.getAllById(roomsIds));
        usersIds.addAll(rooms.stream().map(Room::getUserId).collect(Collectors.toList()));
        userSet.addAll(userDao.getAllById(usersIds));
        result.addAll(userSet);
        return result;
    }
}
