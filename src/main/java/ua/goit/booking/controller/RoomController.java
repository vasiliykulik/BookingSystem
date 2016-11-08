package ua.goit.booking.controller;

import ua.goit.booking.dao.*;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;
import ua.goit.booking.exception.DataCorruptionException;
import ua.goit.booking.exception.OperationFailException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RoomController {
    // повертає масив усіх номерів
    // повертає null, якщо в базі немає номерів
    public List<Room> getAllRooms() {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        result.addAll(roomDao.getAll());
        try {
            if (roomDao.isDataCorrupted(result)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
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

    // повертає масив усіх незайнятих номерів
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getFreeRooms() {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            result.addAll(allRooms.stream()
                    .filter(room -> !room.isBooked(AbstractDaoImp.currentDate, AbstractDaoImp.currentDate)).collect(Collectors.toList()));
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

    // повертає масив усіх номерів, оренда яких не дорожча за (budget)
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getCheaperRooms(int budget) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            result.addAll(allRooms.stream()
                    .filter(room -> (room.getPrice() <= budget)).collect(Collectors.toList()));
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

    // повертає масив усіх незайнятих номерів, оренда яких не дорожча за (budget)
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getFreeCheaperRooms(int budget) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }

        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            result.addAll(allRooms.stream()
                    .filter(room -> (room.getPrice() <= budget) && (!room.isBooked(AbstractDaoImp.currentDate, AbstractDaoImp.currentDate)))
                    .collect(Collectors.toList()));
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

    // повертає масив усіх (nPersons)-вмісних номерів
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getRoomsForNPersons(int nPersons) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            result.addAll(allRooms.stream()
                    .filter(room -> (room.getNumberOfVisitors() == nPersons))
                    .collect(Collectors.toList()));
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

    // повертає масив усіх незайнятих (nPersons)-вмісних номерів
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getFreeRoomsForNPersons(int nPersons) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            result.addAll(allRooms.stream()
                    .filter(room -> (room.getNumberOfVisitors() == nPersons) && (!room.isBooked(AbstractDaoImp.currentDate, AbstractDaoImp.currentDate)))
                    .collect(Collectors.toList()));
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

    // повертає масив усіх номерів міста (theCity)
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getRoomsOfTheCity(String theCity) {
        List<Room> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        List<Hotel> allHotels = hotelDao.getAll();
        try {
            if (hotelDao.isDataCorrupted(allHotels)) {
                throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            hotels.addAll(allHotels.stream()
                    .filter(hotel -> (hotel.getCityName().equals(theCity))).collect(Collectors.toList()));
            for (Hotel hotel : hotels) {
                result.addAll(hotel.getRooms());
            }
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

    // повертає масив усіх незайнятих номерів міста (theCity)
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getFreeRoomsOfTheCity(String theCity) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        List<Hotel> allHotels = hotelDao.getAll();
        try {
            if (hotelDao.isDataCorrupted(allHotels)) {
                throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            hotels.addAll(allHotels.stream()
                    .filter(hotel -> (hotel.getCityName().equals(theCity))).collect(Collectors.toList()));
            for (Hotel hotel : hotels) {
                rooms.addAll(hotel.getRooms());
            }
            result.addAll(rooms.stream().filter(room -> (!room.isBooked(AbstractDaoImp.currentDate, AbstractDaoImp.currentDate))).collect(Collectors.toList()));
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

    // повертає масив усіх номерів, зарезервованих користувачем з id (userId)
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getAllRoomsReservedByUser(Long userId) {
        List<Room> result = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            result.addAll(allRooms.stream()
                    .filter(room -> (room.getUserId().equals(userId))).collect(Collectors.toList()));
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

    // повертає масив усіх номерів, зарезервованих на користувачів з прізвищем (lastName)
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getAllRoomsReservedByUser(String lastName) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        List<User> allUsers = userDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            users.addAll(allUsers.stream()
                    .filter(user -> (user.getLastName().equals(lastName))).collect(Collectors.toList()));
            rooms.addAll(allRooms);
            for (int i = 0; i < users.size(); i++) {
                int finalI = i;
                result.addAll(rooms.stream()
                        .filter(room -> (room.getUserId().equals(users.get(finalI).getId())))
                        .collect(Collectors.toList()));
            }
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

    // повертає масив усіх номерів, зарезервованих на ім'я (firstName) та прізвище (lastName)
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getAllRoomsReservedByUser(String firstName, String lastName) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        List<User> allUsers = userDao.getAll();
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            users.addAll(allUsers.stream()
                    .filter(user -> (user.getFirstName().equals(firstName)
                            && user.getLastName().equals(lastName))).collect(Collectors.toList()));
            rooms.addAll(allRooms);
            for (int i = 0; i < users.size(); i++) {
                int finalI = i;
                result.addAll(rooms.stream()
                        .filter(room -> (room.getUserId().equals(users.get(finalI).getId())))
                        .collect(Collectors.toList()));
            }
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

    // за даним ID (roomId) номеру повертає масив усіх номерів з цього готелю
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getAllRoomsFromThisHotel(Long roomId) {
        List<Room> result = null;
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        List<Room> allRooms = roomDao.getAll();
        List<Hotel> allHotels = hotelDao.getAll();
        Room room;
        try {
            if (hotelDao.isDataCorrupted(allHotels)) {
                throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            rooms.addAll(allRooms);
            room = roomDao.getById(roomId);
            if (!rooms.contains(room)) {
                try {
                    throw new OperationFailException("There's no room with such ID in DB!");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return null;
            }
            result = hotelDao.getById(room.getHotelId()).getRooms();
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

    // за даним ID (roomId) номеру повертає масив усіх незайнятих номерів з цього готелю
    // повертає null, якщо таких номерів не знайдено
    public List<Room> getAllFreeRoomsFromThisHotel(Long roomId) {
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        List<Hotel> allHotels = hotelDao.getAll();
        List<Room> allRooms = roomDao.getAll();
        Room room;
        try {
            if (hotelDao.isDataCorrupted(allHotels)) {
                throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            if (roomDao.isDataCorrupted(allRooms)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            rooms.addAll(allRooms);
            room = roomDao.getById(roomId);
            if (!rooms.contains(room)) {
                try {
                    throw new OperationFailException("There's no room with such (roomId) in DB!");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return null;
            }
            rooms = hotelDao.getById(room.getHotelId()).getRooms();
            result.addAll(rooms.stream().filter(room1 -> (!room1.isBooked(AbstractDaoImp.currentDate, AbstractDaoImp.currentDate))).collect(Collectors.toList()));
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
}
