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
    // повертає масив усіх користувачів
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Long> roomsIds = new ArrayList<>();
        Set<Long> userIds = new HashSet<>();
        AbstractDao<User> userDao = new UserDaoImpl();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();

        hotels.addAll(hotelDao.getAll());
        for (Hotel hotel : hotels) {
            roomsIds.addAll(hotel.getRoomsId());
        }
        userIds.addAll(roomsIds.stream()
                .map(roomsId -> roomDao.getById(roomsId).getUserId()).collect(Collectors.toList()));
        result.addAll(userIds.stream().map(userDao::getById).collect(Collectors.toList()));

        return result;
    }
}
