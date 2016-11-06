package ua.goit.booking.controller;

import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomController {
    public List<Room> getAllRooms() {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll());
        return result;
    }

    public List<Room> getFreeRooms() {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(room -> !room.isBooked()).collect(Collectors.toList()));
        return result;
    }

    public List<Room> getCheaperRooms(int budget) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(room -> (room.getPrice() <= budget)).collect(Collectors.toList()));
        return result;
    }

    public List<Room> getFreeCheaperRooms(int budget) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(room -> (room.getPrice() <= budget) & (!room.isBooked())).collect(Collectors.toList()));
        return result;
    }

    public List<Room> getRoomsForNPersons(int nPersons) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(room -> (room.getNumberOfVisitors() == nPersons))
                .collect(Collectors.toList()));
        return result;
    }

    public List<Room> getFreeRoomsForNPersons(int nPersons) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(room -> (room.getNumberOfVisitors() == nPersons) & (!room.isBooked()))
                .collect(Collectors.toList()));
        return result;
    }

    public List<Room> getRoomsOfTheCity(String theCity) {
        List<Room> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        hotels.addAll(hotelDao.getAll().stream()
                .filter(hotel -> (hotel.getCityName().equals(theCity))).collect(Collectors.toList()));
        for (Hotel hotel : hotels) {
            result.addAll(hotel.getRooms());
        }
        return result;
    }

}
