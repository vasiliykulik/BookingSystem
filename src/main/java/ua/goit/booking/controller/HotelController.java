package ua.goit.booking.controller;

import ua.goit.booking.controller.exception.HotelControllerException;
import ua.goit.booking.dao.*;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.HotelDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.exception.DataCorruptionException;
import ua.goit.booking.exception.OperationFailException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /*public void bookRoom(long roomId, long userId, long hotelId, Date fromDate, Date toDate) {
        UserDao userDao = new UserDaoImpl();
        HotelDao hotelDao = new HotelDaoImpl();
        List<Hotel> hotels = hotelDao.getAll();
        try {
            try {
                if (hotelDao.isDataCorrupted(hotels)) {
                    throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
                }
            } catch (DataCorruptionException dce) {
                dce.printStackTrace();
            }
            userDao.isLoggedIn(userId);
            for (Hotel hotel : hotels) {
                if (hotelId == hotel.getId()) {
                    for (Room room : hotel.getRooms()) {
                        if (roomId == room.getId()) {
                            if (!room.isBooked(fromDate, toDate)) {
                                room.setFromDate(fromDate);
                                room.setToDate(toDate);
                                room.setUserId(userId);
                                hotelDao.updateBase(hotels);
                                return;
                            } else {
                                try {
                                    throw new OperationFailException("Sorry! " + room + " has already booked!");
                                } catch (OperationFailException ope) {
                                    ope.printStackTrace();
                                }
                                return;
                            }
                        }
                    }
                    try {
                        throw new OperationFailException("Sorry! There's no such room in the hotel.");
                    } catch (OperationFailException ope) {
                        ope.printStackTrace();
                    }
                    return;
                }
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        try {
            throw new OperationFailException("Sorry! There's no such hotels.");
        } catch (OperationFailException ofe) {
            ofe.printStackTrace();
        }
    }*/

    /*public void cancelReservation(long roomId, long userId, long hotelId) {
        UserDao userDao = new UserDaoImpl();
        HotelDao hotelDao = new HotelDaoImpl();
        List<Hotel> hotels = hotelDao.getAll();
        try {
            try {
                if (hotelDao.isDataCorrupted(hotels)) {
                    throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
                }
            } catch (DataCorruptionException dce) {
                dce.printStackTrace();
            }
            userDao.isLoggedIn(userId);
            for (Hotel hotel : hotels) {
                if (hotelId == hotel.getId()) {
                    for (Room room : hotel.getRooms()) {
                        if (roomId == room.getId()) {
                            if (room.getUserId() != null) {
                                if (room.getUserId() == userId) {
                                    room.setToDate(room.getFromDate());
                                    room.setUserId(null);
                                    hotelDao.updateBase(hotels);
                                    return;
                                } else {
                                    try {
                                        throw new OperationFailException("Sorry! You haven't booked this room!");
                                    } catch (OperationFailException ofe) {
                                        ofe.printStackTrace();
                                    }
                                    return;
                                }
                            } else {
                                try {
                                    throw new OperationFailException("Sorry! " + room + " has not booked!");
                                } catch (OperationFailException ofe) {
                                    ofe.printStackTrace();
                                }
                                return;
                            }
                        }
                    }
                    try {
                        throw new OperationFailException("Sorry! There's no such room in the hotel.");
                    } catch (OperationFailException ofe) {
                        ofe.printStackTrace();
                    }
                    return;
                }
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        try {
            throw new OperationFailException("Sorry! There's no such hotels.");
        } catch (OperationFailException ofe) {
            ofe.printStackTrace();
        }
    }*/

    public List<Hotel> findRoom(Map<String, String> params) {
        List<Hotel> result = null;
        Set<Hotel> hotels = new HashSet<>();
        try {
//            try {
//                if (hotelDao.isDataCorrupted(hotelDao.getAll())) {
//                    throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
//                }
//            } catch (DataCorruptionException dce) {
//                dce.printStackTrace();
//            }
            for (String key : params.keySet()) {
                for (Field field : Room.getFieldsName()) {
                    if (field.getName().equals(key)) {
                        String value = params.get(key);
                        for (Hotel hotel : hotelDao.getAll()) {
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
                                        hotels.add(hotel);
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
            result = new ArrayList<Hotel>(hotels);
            if (result.isEmpty()) {
                try {
                    throw new OperationFailException("There's no such rooms.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return null;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return result;
    }

    /*public List<Room> getAllFreeRooms(Long hotelId) {
        List<Room> result = new ArrayList<>();
        HotelDao hotelDao = new HotelDaoImpl();
        Date currentDate = Calendar.getInstance().getTime();
        try {
            try {
                if (hotelDao.isDataCorrupted(hotelDao.getAll())) {
                    throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
                }
            } catch (DataCorruptionException dce) {
                dce.printStackTrace();
            }
            result = hotelDao.getById(hotelId).getRooms().stream()
                    .filter(room -> !(room.getToDate().after(currentDate) && room.getFromDate().before(currentDate)))
                    .collect(Collectors.toList());
            if (result.isEmpty()) {
                try {
                    throw new OperationFailException("There's no such rooms.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return null;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return result;
    }*/

    public Hotel save(Hotel hotel) throws HotelControllerException {
        HotelDao hotelDao = new HotelDaoImpl();
        Hotel result = null;
        try {
            result = hotelDao.save(hotel);
        } catch (AbstractDaoException e) {
            throw new HotelControllerException(e.getMessage());
        }
        return result;
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
}
