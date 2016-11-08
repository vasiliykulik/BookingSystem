package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.io.File;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class HotelDaoImpl extends AbstractDaoImp<Hotel> implements HotelDao {

    public HotelDaoImpl() {
        super(new File("static/hotels.json"), new TypeReference<List<Hotel>>() {
        });
    }

    @Override
    public Hotel save(Hotel hotel) {
        //TODO Logic
        return null;
    }

    @Override
    public boolean delete(Hotel hotel) {
        //TODO Logic
        return false;
    }

    @Override
    public boolean isDataCorrupted(List<Hotel> hotelList) {
        Hotel hotel;
        List<Long> roomsIds;
        if (hotelList == null) {
            return true;
        }
        if (hotelList.isEmpty()) {
            return true;
        }
        for (Hotel aHotelList : hotelList) {
            hotel = aHotelList;
            if (hotel == null) {
                return true;
            }
            roomsIds = hotel.getRoomsId();
            if (hotel.getId() == null || hotel.getHotelName() == null
                    || hotel.getCityName() == null || roomsIds == null) {
                return true;
            }
            for (Long roomsId : roomsIds) {
                if (roomsId == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Room addRoom(Hotel hotel, Room room) {
        if (room == null
                || hotel == null
                || room.getHotelId() != hotel.getId()) {
            System.out.println("This room cannot be saved!");
            //TODO Exception
            return null;
        }
        List<Long> roomsId = hotel.getRoomsId();
        if (roomsId.contains(room.getId())) {
            return room;
        }
        roomsId.add(room.getId());
        //TODO updateBase hotel in DB
//        List<Hotel> hotels = getAll();
//        for (Hotel oldHotel : hotels) {
//            if (oldHotel.getId() == hotel.getId()) {
//                oldHotel = hotel;
//            }
//        }
        return room;
    }
}