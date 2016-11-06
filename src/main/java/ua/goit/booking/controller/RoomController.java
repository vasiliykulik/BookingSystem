package ua.goit.booking.controller;

import ua.goit.booking.entity.Room;
import ua.goit.booking.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomController {
    public List<Room> getAllFreeRooms() {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll().stream().filter(room -> !room.isBooked()).collect(Collectors.toList()));
        return result;
    }

    public List<Room> getCheaperRooms(int budget) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> dao = new RoomDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(room -> (room.getPrice() <= budget)&(!room.isBooked())).collect(Collectors.toList()));
        return result;
    }


}
