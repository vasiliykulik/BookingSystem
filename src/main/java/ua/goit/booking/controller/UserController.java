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
    // повертає null, якщо в базі немає користувачів
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        AbstractDao<User> userDao = new UserDaoImpl();
        Set<User> userSet = new HashSet<>();
        userSet.addAll(userDao.getAll());
        result.addAll(userSet);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    // за даним id готелю (hotelId) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    // повертає null, якщо в готелі не зареєстровано жодного користувача
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
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    // за даною назвою готелю (hotelName) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    // повертає null, якщо в готелі не зареєстровано жодного користувача
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
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    // за даною назвою міста (theCity) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    // повертає null, якщо таких користувачів не знайдено
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
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    // за даним ID користувача (userId) повертає кількість зарезервованих на нього номерів
    // повертає -1, якщо користувача з таким (userId) немає в базі
    public int getRoomsQuantity(Long userId) {
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        rooms.addAll(roomDao.getAll().stream()
                .filter(room -> (room.getUserId().equals(userId))).collect(Collectors.toList()));
        if (rooms.isEmpty()) {
            return -1;
        }
        return rooms.size();
    }

    // повертає кількість номерів, зарезервованих на користувачів з прізвищем (lastName)
    // повертає -1, якщо користувача з таким (lastName) немає в базі
    public int getRoomsQuantity(String lastName) {
        List<Room> resultRooms = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        users.addAll(userDao.getAll().stream()
                .filter(user -> (user.getLastName().equals(lastName))).collect(Collectors.toList()));
        rooms.addAll(roomDao.getAll());
        for (int i = 0; i < users.size(); i++) {
            int finalI = i;
            resultRooms.addAll(rooms.stream()
                    .filter(room -> (room.getUserId().equals(users.get(finalI).getId()))).collect(Collectors.toList()));
        }
        if (resultRooms.isEmpty()) {
            return -1;
        }
        return resultRooms.size();
    }

    // повертає кількість номерів, зарезервованих на ім'я (firstName) та прізвище (lastName)
    // повертає -1, якщо користувача з таким (firstName) та (lastName) немає в базі
    public int getRoomsQuantity(String firstName, String lastName) {
        List<Room> resultRooms = new ArrayList<>();
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
            resultRooms.addAll(rooms.stream()
                    .filter(room -> (room.getUserId().equals(users.get(finalI).getId()))).collect(Collectors.toList()));
        }
        if (resultRooms.isEmpty()) {
            return -1;
        }
        return resultRooms.size();
    }

    // повертає суму коштів, витрачених користувачем з ID (userId) на бронювання номерів
    // повертає -1, якщо користувача з таким (userId) немає в базі
    public long getUserBudget(Long userId) {
        long budget = 0L;
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        rooms.addAll(roomDao.getAll().stream()
                .filter(room -> (room.getUserId().equals(userId))).collect(Collectors.toList()));
        if (rooms.isEmpty()) {
            return -1;
        }
        for (Room room : rooms) {
            budget += room.getPrice();
        }
        return budget;
    }

    // повертає суму коштів, витрачених користувачами з прізвищем (lastName) на бронювання номерів
    // повертає -1, якщо користувачів з таким (lastName) немає в базі
    public long getUserBudget(String lastName) {
        long budget = 0L;
        List<Room> resultRooms = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        users.addAll(userDao.getAll().stream()
                .filter(user -> (user.getLastName().equals(lastName))).collect(Collectors.toList()));
        rooms.addAll(roomDao.getAll());
        for (int i = 0; i < users.size(); i++) {
            int finalI = i;
            resultRooms.addAll(rooms.stream()
                    .filter(room -> (room.getUserId().equals(users.get(finalI).getId()))).collect(Collectors.toList()));
        }
        if (resultRooms.isEmpty()) {
            return -1;
        }
        for (Room resultRoom : resultRooms) {
            budget += resultRoom.getPrice();
        }
        return budget;
    }

    // повертає суму коштів, витрачених користувачами
    // з іменем (firstName) та прізвищем (lastName) на бронювання номерів
    // повертає -1, якщо користувачів з такими (firstName) та (lastName) немає в базі
    public long getUserBudget(String firstName, String lastName) {
        long budget = 0L;
        List<Room> resultRooms = new ArrayList<>();
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
            resultRooms.addAll(rooms.stream()
                    .filter(room -> (room.getUserId().equals(users.get(finalI).getId()))).collect(Collectors.toList()));
        }
        if (resultRooms.isEmpty()) {
            return -1;
        }
        for (Room resultRoom : resultRooms) {
            budget += resultRoom.getPrice();
        }
        return budget;
    }
}
