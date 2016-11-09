package ua.goit.booking.controller;

import ua.goit.booking.dao.*;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;
import ua.goit.booking.exception.DataCorruptionException;
import ua.goit.booking.exception.OperationFailException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserController {

    private static UserDao userDao = new UserDaoImpl();


    public void save(User user) {
        try {
            userDao.save(user);
        } catch (AbstractDaoException e) {
            e.printStackTrace();
        }
    }
    // повертає масив усіх користувачів (без дублювання)
    // повертає null, якщо в базі немає користувачів
    /*public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        AbstractDao<User> userDao = new UserDaoImpl();
        List<User> allUsers = userDao.getAll();
        Set<User> userSet = new HashSet<>();
        try {
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            userSet.addAll(allUsers);
            result.addAll(userSet);
            if (result.isEmpty()) {
                try {
                    throw new OperationFailException("Users not found.");
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

    // за даним id готелю (hotelId) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    // повертає null, якщо в готелі не зареєстровано жодного користувача
    /*public List<User> getAllUsersFromHotel(long hotelId) {
        List<User> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        Set<User> userSet = new HashSet<>();
        List<Hotel> allHotels = hotelDao.getAll();
        Hotel hotel;
        try {
            if (hotelDao.isDataCorrupted(allHotels)) {
                throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            hotels.addAll(allHotels);
            hotel = hotelDao.getById(hotelId);
            if (!hotels.contains(hotel)) {
                try {
                    throw new OperationFailException("There's no hotel with such (hotelId) in DB!");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return null;
            }
            rooms.addAll(hotel.getRooms());
            userSet.addAll(rooms.stream().map(Room::getUser).collect(Collectors.toList()));
            result.addAll(userSet);
            if (result.isEmpty()) {
                try {
                    throw new OperationFailException("Users not found.");
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

    // за даною назвою готелю (hotelName) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    // повертає null, якщо в готелі не зареєстровано жодного користувача
    /*public List<User> getAllUsersFromHotel(String hotelName) {
        List<User> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        Set<User> userSet = new HashSet<>();
        Hotel ourHotel = null;
        List<Hotel> allHotels = hotelDao.getAll();
        try {
            if (hotelDao.isDataCorrupted(allHotels)) {
                throw new DataCorruptionException("WARNING! List<Hotel> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            hotels.addAll(allHotels);
            for (Hotel hotelTmp : hotels) {
                if (hotelTmp.getHotelName().equals(hotelName)) {
                    ourHotel = hotelTmp;
                    break;
                }
            }
            if (ourHotel == null) {
                try {
                    throw new OperationFailException("There's no hotel with such (hotelName) in DB!");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return null;
            }
            rooms.addAll(ourHotel.getRooms());
            userSet.addAll(rooms.stream().map(Room::getUser).collect(Collectors.toList()));
            result.addAll(userSet);
            if (result.isEmpty()) {
                try {
                    throw new OperationFailException("Users not found.");
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

    // за даною назвою міста (theCity) повертає масив усіх зареєстрованих у ньому користувачів (без дублювання)
    // повертає null, якщо таких користувачів не знайдено
    /*public List<User> getAllUsersFromCity(String theCity) {
        List<User> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        List<Long> roomsIds = new ArrayList<>();
        List<Long> usersIds = new ArrayList<>();
        AbstractDao<Hotel> hotelDao = new HotelDaoImpl();
        AbstractDao<Room> roomDao = new RoomDaoImpl();
        AbstractDao<User> userDao = new UserDaoImpl();
        Set<User> userSet = new HashSet<>();
        List<Hotel> allHotels = hotelDao.getAll();
        List<Room> allRooms = roomDao.getAll();
        List<User> allUsers = userDao.getAll();
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
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            hotels.addAll(allHotels.stream()
                    .filter(hotel -> (hotel.getCityName().equals(theCity))).collect(Collectors.toList()));
            if (hotels.isEmpty()) {
                try {
                    throw new OperationFailException("There's no such city in DB!");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
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
                try {
                    throw new OperationFailException("Users not found.");
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

    // за даним ID користувача (userId) повертає кількість зарезервованих на нього номерів
    // повертає -1, якщо користувача з таким (userId) немає в базі
    /*public int getRoomsQuantity(Long userId) {
        List<Room> rooms = new ArrayList<>();
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
            rooms.addAll(allRooms.stream()
                    .filter(room -> (room.getUserId().equals(userId))).collect(Collectors.toList()));
            if (rooms.isEmpty()) {
                try {
                    throw new OperationFailException("No rooms booked on this userId.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return -1;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return rooms.size();
    }*/

    // повертає кількість номерів, зарезервованих на користувачів з прізвищем (lastName)
    // повертає -1, якщо користувача з таким (lastName) немає в базі
    /*public int getRoomsQuantity(String lastName) {
        List<Room> resultRooms = new ArrayList<>();
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
            rooms.addAll(roomDao.getAll());
            for (int i = 0; i < users.size(); i++) {
                int finalI = i;
                resultRooms.addAll(rooms.stream()
                        .filter(room -> (room.getUserId().equals(users.get(finalI).getId())))
                        .collect(Collectors.toList()));
            }
            if (resultRooms.isEmpty()) {
                try {
                    throw new OperationFailException("No rooms booked on this lastName.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return -1;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return resultRooms.size();
    }*/

    // повертає кількість номерів, зарезервованих на ім'я (firstName) та прізвище (lastName)
    // повертає -1, якщо користувача з таким (firstName) та (lastName) немає в базі
    /*public int getRoomsQuantity(String firstName, String lastName) {
        List<Room> resultRooms = new ArrayList<>();
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
                resultRooms.addAll(rooms.stream()
                        .filter(room -> (room.getUserId().equals(users.get(finalI).getId())))
                        .collect(Collectors.toList()));
            }
            if (resultRooms.isEmpty()) {
                try {
                    throw new OperationFailException("No rooms booked on this firstName & lastName.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }

                return -1;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return resultRooms.size();
    }*/

    // повертає суму коштів, витрачених користувачем з ID (userId) на бронювання номерів
    // повертає -1, якщо користувача з таким (userId) немає в базі
    /*public long getUserBudget(Long userId) {
        long budget = 0L;
        List<Room> rooms = new ArrayList<>();
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
            rooms.addAll(allRooms.stream()
                    .filter(room -> (room.getUserId().equals(userId))).collect(Collectors.toList()));
            if (rooms.isEmpty()) {
                try {
                    throw new OperationFailException("No rooms booked on this userId.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return -1;
            }
            for (Room room : rooms) {
                budget += room.getPrice();
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return budget;
    }*/

    // повертає суму коштів, витрачених користувачами з прізвищем (lastName) на бронювання номерів
    // повертає -1, якщо користувачів з таким (lastName) немає в базі
    /*public long getUserBudget(String lastName) {
        long budget = 0L;
        List<Room> resultRooms = new ArrayList<>();
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
                resultRooms.addAll(rooms.stream()
                        .filter(room -> (room.getUserId().equals(users.get(finalI).getId())))
                        .collect(Collectors.toList()));
            }
            if (resultRooms.isEmpty()) {
                try {
                    throw new OperationFailException("No rooms booked on this lastName.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return -1;
            }
            for (Room resultRoom : resultRooms) {
                budget += resultRoom.getPrice();
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return budget;
    }*/

    // повертає суму коштів, витрачених користувачами
    // з іменем (firstName) та прізвищем (lastName) на бронювання номерів
    // повертає -1, якщо користувачів з такими (firstName) та (lastName) немає в базі
   /* public long getUserBudget(String firstName, String lastName) {
        long budget = 0L;
        List<Room> resultRooms = new ArrayList<>();
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
                resultRooms.addAll(rooms.stream()
                        .filter(room -> (room.getUserId().equals(users.get(finalI).getId())))
                        .collect(Collectors.toList()));
            }
            if (resultRooms.isEmpty()) {
                try {
                    throw new OperationFailException("No rooms booked on this firstName & lastName.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return -1;
            }
            for (Room resultRoom : resultRooms) {
                budget += resultRoom.getPrice();
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return budget;
    }*/

    /*public User logIn(User user) {
        UserDao userDao = new UserDaoImpl();
        List<User> allUsers = userDao.getAll();
        User user1 = null;
        try {
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            user1 = userDao.save(user);
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return user1;
    }*/

    /*public boolean deleteUser(User user) {
        UserDao userDao = new UserDaoImpl();
        List<User> allUsers = userDao.getAll();
        boolean successFlag = false;
        try {
            if (userDao.isDataCorrupted(allUsers)) {
                throw new DataCorruptionException("WARNING! List<User> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        try {
            successFlag = userDao.delete(user);
            if (!successFlag) {
                try {
                    throw new OperationFailException("Can't delete " + user);
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return successFlag;
    }*/

}
