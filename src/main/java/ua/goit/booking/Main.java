package ua.goit.booking;

import ua.goit.booking.dao.*;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hotels\n===");
        HotelDao hotelDAO = new HotelDaoImpl();
        hotelDAO.getAll().forEach(System.out::println);

        System.out.println("Rooms\n===");
        RoomDao roomDao = new RoomDaoImpl();
        List<Room> toUpdate = roomDao.getAll();
        toUpdate.forEach(room -> room.setUserId(null));
        roomDao.update(toUpdate);
        roomDao.getAll().forEach(System.out::println);

        System.out.println("Users\n===");
        UserDao userDao = new UserDaoImpl();
        User user = new User("Oleg", "Orlov", "olegik.orlov@gmail.com");
        List<User> users = new ArrayList<>();
        users.add(user);
        userDao.update(users); //
        userDao.getAll().forEach(System.out::println);
//        hotelDAO.findHotelByName("Plaza").forEach(System.out::println);
//        hotelDAO.findHotelDyCity("Kiyv").forEach(System.out::println);
//        hotelDAO.bookRoom(6675363734328343759l, 1, 8577383723364764120l);
//        hotelDAO.cancelReservation(6675363734328343759l, 1, 8577383723364764120l);
//        hotelDAO.cancelReservation(345346346l, 1, 232345l);

        Map<String, String> map = new HashMap<>();
        map.put("price", "334");
        map.put("person", "3");
//        System.out.println(hotelDAO.findRoom(map));

    }
}
