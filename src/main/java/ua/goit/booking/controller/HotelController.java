package ua.goit.booking.controller;

import ua.goit.booking.dao.*;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class HotelController {

    public List<Hotel> findHotelByName(String name) {
        List<Hotel> result = new ArrayList<>();
        AbstractDao<Hotel> dao = new HotelDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(hotel -> hotel.getHotelName().equals(name)).collect(Collectors.toList()));
        return result;
    }

    public List<Hotel> findHotelByCity(String city) {
        List<Hotel> result = new ArrayList<>();
        HotelDao dao = new HotelDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(hotel -> hotel.getCityName().equals(city)).collect(Collectors.toList()));
        return result;
    }

    public void bookRoom(long roomId, long userId, long hotelId, Date fromDate, Date toDate) {
        HotelDao dao = new HotelDaoImpl();
        List<Hotel> hotels = dao.getAll();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (!room.isBooked()) {
                            room.setFromDate(fromDate);
                            room.setToDate(toDate);
                            room.setUserId(userId);
                            System.out.println("Success! " + room + " was booked!");
                            dao.update(hotels);
                            return;
                        } else {
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
        HotelDao dao = new HotelDaoImpl();
        List<Hotel> hotels = dao.getAll();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (room.isBooked()) {
                            if (room.getUserId() == userId) {
                                room.setToDate(room.getFromDate());
                                room.setUserId(null);
                                System.out.println("Success! " + room + " reservation was canceled!");
                                dao.update(hotels);
                                return;
                            } else {
                                //TODO Exception
                                System.out.println("Sorry! You did not booked this room!");
                                return;
                            }
                        } else {
                            //TODO Exception
                            System.out.println("Sorry! " + room + " is not booked!");
                            return;
                        }
                    }
                }
                //TODO Exception
                System.out.println("Sorry! No such room in the hotel.");
                return;
            }
        }
        System.out.println("Sorry! No such hotel.");
        return;
    }

    public List<Hotel> findRoom(Map<String, String> params) {
        Set<Hotel> result = new HashSet<>();
        HotelDao dao = new HotelDaoImpl();

        for (String key : params.keySet()) {
            for (Field field : Room.getFieldsName()) {
                if (field.getName().equals(key)) {
                    String value = params.get(key);
                    for (Hotel hotel : dao.getAll()) {
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

    public List<Room> getAllFreeRooms(Long hotelId) {
        HotelDao hotelDao = new HotelDaoImpl();
        Date currentDate = Calendar.getInstance().getTime();
        List<Room> result = hotelDao.getById(hotelId).getRooms().stream()
                .filter(room -> !(room.getToDate().after(currentDate) && room.getFromDate().before(currentDate)))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

}
