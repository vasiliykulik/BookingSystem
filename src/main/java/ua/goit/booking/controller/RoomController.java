package ua.goit.booking.controller;

import ua.goit.booking.dao.AbstractDao;
import ua.goit.booking.dao.HotelDaoImpl;
import ua.goit.booking.dao.RoomDaoImpl;
import ua.goit.booking.dao.UserDaoImpl;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomController {
    // повертає масив усіх номерів
    public List<Room> getAllRooms() {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll());
        return result;
    }

    // повертає масив усіх незайнятих номерів
    public List<Room> getFreeRooms() {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll().stream()
                .filter(room -> !room.isBooked()).collect(Collectors.toList()));
        return result;
    }

    // повертає масив усіх номерів, оренда яких не дорожча за (budget)
    public List<Room> getCheaperRooms(int budget) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll().stream()
                .filter(room -> (room.getPrice() <= budget)).collect(Collectors.toList()));
        return result;
    }

    // повертає масив усіх незайнятих номерів, оренда яких не дорожча за (budget)
    public List<Room> getFreeCheaperRooms(int budget) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll().stream()
                .filter(room -> (room.getPrice() <= budget) && (!room.isBooked())).collect(Collectors.toList()));
        return result;
    }

    // повертає масив усіх (nPersons)-вмісних номерів
    public List<Room> getRoomsForNPersons(int nPersons) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll().stream()
                .filter(room -> (room.getNumberOfVisitors() == nPersons))
                .collect(Collectors.toList()));
        return result;
    }

    // повертає масив усіх незайнятих (nPersons)-вмісних номерів
    public List<Room> getFreeRoomsForNPersons(int nPersons) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll().stream()
                .filter(room -> (room.getNumberOfVisitors() == nPersons) && (!room.isBooked()))
                .collect(Collectors.toList()));
        return result;
    }

    // повертає масив усіх номерів міста (theCity)
    public List<Room> getRoomsOfTheCity(String theCity) {
        List<Room> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        hotels.addAll(hotelDao.getAll().stream()
                .filter(hotel -> (hotel.getCityName().equals(theCity))).collect(Collectors.toList()));
        for (Hotel hotel : hotels) {
            result.addAll(hotel.getRooms());
        }
        return result;
    }

    // повертає масив усіх незайнятих номерів міста (theCity)
    public List<Room> getFreeRoomsOfTheCity(String theCity) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        hotels.addAll(hotelDao.getAll().stream()
                .filter(hotel -> (hotel.getCityName().equals(theCity))).collect(Collectors.toList()));
        for (Hotel hotel : hotels) {
            rooms.addAll(hotel.getRooms());
        }
        result.addAll(rooms.stream().filter(room -> (!room.isBooked())).collect(Collectors.toList()));
        return result;
    }

    // повертає масив усіх номерів, зарезервованих користувачем з id (userId)
    public List<Room> getAllRoomsReservedByUser(Long userId) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll().stream()
                .filter(room -> (room.getUserId().equals(userId))).collect(Collectors.toList()));
        return result;
    }

    // повертає масив усіх номерів, зарезервованих на користувачів з прізвищем (lastName)
    public List<Room> getAllRoomsReservedByUser(String lastName) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        users.addAll(userDao.getAll().stream()
                .filter(user -> (user.getLastName().equals(lastName))).collect(Collectors.toList()));
        rooms.addAll(roomDao.getAll());
        for (int i = 0; i < users.size(); i++) {
            int finalI = i;
            result.addAll(rooms.stream()
                    .filter(room -> (room.getUserId().equals(users.get(finalI).getId()))).collect(Collectors.toList()));
        }
        return result;
    }

    // повертає масив усіх номерів, зарезервованих на ім'я (firstName) та прізвище (lastName)
    public List<Room> getAllRoomsReservedByUser(String firstName, String lastName) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        users.addAll(userDao.getAll().stream()
                .filter(user -> (user.getFirstName().equals(firstName)
                        && user.getLastName().equals(lastName))).collect(Collectors.toList()));
        rooms.addAll(roomDao.getAll());
        for (int i = 0; i < users.size(); i++) {
            int finalI = i;
            result.addAll(rooms.stream()
                    .filter(room -> (room.getUserId().equals(users.get(finalI).getId()))).collect(Collectors.toList()));
        }
        return result;
    }

    // за даним ID (roomId) номеру повертає масив усіх номерів з його готелю
    public List<Room> getAllRoomsFromThisHotel(Long roomId) {
        List<Room> result;
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        Room room;
        rooms.addAll(roomDao.getAll());
        room = roomDao.getById(roomId);
        if (!rooms.contains(room)) {
            //  System.out.println("There's no room with such ID in DB!");
            return null;
        }
        result = hotelDao.getById(room.getHotelId()).getRooms();
        return result;
    }

    // за даним ID (roomId) номеру повертає масив усіх незайнятих номерів з його готелю
    public List<Room> getAllFreeRoomsFromThisHotel(Long roomId) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        Room room;
        rooms.addAll(roomDao.getAll());
        room = roomDao.getById(roomId);
        if (!rooms.contains(room)) {
            //  System.out.println("There's no room with such (roomId) in DB!");
            return null;
        }
        rooms = hotelDao.getById(room.getHotelId()).getRooms();
        result.addAll(rooms.stream().filter(room1 -> (!room1.isBooked())).collect(Collectors.toList()));
        return result;
    }
}
