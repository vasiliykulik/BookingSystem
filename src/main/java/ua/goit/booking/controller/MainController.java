package ua.goit.booking.controller;

import ua.goit.booking.dao.HotelDAO;
import ua.goit.booking.dao.HotelDAOImpl;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by taras on 05.11.16.
 */
public class MainController {

    public List<Hotel> findHotelByName(String name) {
        List<Hotel> result = new ArrayList<>();
        HotelDAO dao = new HotelDAOImpl();
        for (Hotel hotel : dao.getAllHotels()) {
            if (hotel.getHotelName().equals(name)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public List<Hotel> findHotelDyCity(String city) {
        List<Hotel> result = new ArrayList<>();
        HotelDAO dao = new HotelDAOImpl();
        for (Hotel hotel : dao.getAllHotels()) {
            if (hotel.getCityName().equals(city)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public void bookRoom(long roomId, long userId, long hotelId) {
        HotelDAO dao = new HotelDAOImpl();
        List<Hotel> hotels = dao.getAllHotels();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (!room.isBooked()) {
                            room.setBooked(true);
                            room.setUserBookedId(userId);
                            System.out.println("Success! " + room + " was booked!");
                            dao.update(hotels);
                            return;
                        }
                        else {
                            System.out.println("Sorry! " + room + " is already booked!");
                            return;
                        }
                    }
                }
                System.out.println("Sorry! No such room in the hotel.");
                return;
            }
        }
        System.out.println("Sorry! No such hotel.");
        return;
    }

    public void cancelReservation(long roomId, long userId, long hotelId) {
        HotelDAO dao = new HotelDAOImpl();
        List<Hotel> hotels = dao.getAllHotels();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (room.isBooked()) {
                            if (room.getUserBookedId() == userId){
                                room.setBooked(false);
                                room.setUserBookedId(0l);
                                System.out.println("Success! " + room + " reservation was canceled!");
                                dao.update(hotels);
                                return;
                            }
                            else {
                                System.out.println("Sorry! You did not booked this room!");
                                return;
                            }
                        }
                        else {
                            System.out.println("Sorry! " + room + " is not booked!");
                            return;
                        }
                    }
                }
                System.out.println("Sorry! No such room in the hotel.");
                return;
            }
        }
        System.out.println("Sorry! No such hotel.");
        return;
    }

    public List<Hotel> findRoom(Map<String, String> params) {
        Set<Hotel> result = new HashSet<>();
        HotelDAO dao = new HotelDAOImpl();

        for (String key : params.keySet()) {
            for (Field field : Room.getFieldsName()) {
                if (field.getName().equals(key)) {
                    String value = params.get(key);
                    for (Hotel hotel : dao.getAllHotels()) {
                        for (Room room : hotel.getRooms()) {
                            Field f = null;
                            try {
                                f = room.getClass().getDeclaredField(key);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                            if (f != null) {
                                f.setAccessible(true);
                            }
                            try {
                                if (f != null && f.get(room).toString().equals(value)) {
                                    result.add(hotel);
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<Hotel>(result);
    }

}
