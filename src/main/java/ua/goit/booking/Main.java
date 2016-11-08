package ua.goit.booking;

import ua.goit.booking.controller.HotelController;
import ua.goit.booking.controller.RoomController;
import ua.goit.booking.controller.UserController;
import ua.goit.booking.dao.*;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;
import ua.goit.booking.exception.DataCorruptionException;

import java.util.*;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Hotels\n===");
//        HotelDao hotelDAO = new HotelDaoImpl();
//        hotelDAO.getAll().forEach(System.out::println);
//
//        System.out.println("Rooms\n===");
//        RoomDao roomDao = new RoomDaoImpl();
//        List<Room> toUpdate = roomDao.getAll();1
//        toUpdate.forEach(room -> room.setUserId(null));
//        roomDao.updateBase(toUpdate);
//        roomDao.getAll().forEach(System.out::println);
//
//        System.out.println("Users\n===");
//        UserDao userDao = new UserDaoImpl();
//        User user = new User("Oleg", "Orlov", "olegik.orlov@gmail.com");
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        userDao.updateBase(users); //
//        userDao.getAll().forEach(System.out::println);

//        UserController userController = new UserController();
//        userController.getAllUsers().forEach(System.out::println);


        HotelDao hotelDao = new HotelDaoImpl();
//        System.out.println(hotelDao.getById(6124218799539178536L));

        Room room = new Room(350, 3, 6124218799539178536L);

        Date currentDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 15, 10, 00);
        Date endDate = calendar.getTime();

 /*       RoomController roomController = new RoomController();
//        List<Room> rooms = roomController.getAllRooms();
//        for (Room room : rooms) {
//            room.setToDate(currentDate);
//            room.setFromDate(currentDate);
//        }
//        RoomDao roomDao = new RoomDaoImpl();
//        roomDao.updateBase(rooms);
        roomController.getFreeRooms().forEach(System.out::println);*/


//        hotelDAO.findHotelByName("Plaza").forEach(System.out::println);
//        hotelDAO.findHotelDyCity("Kiyv").forEach(System.out::println);
//        hotelDAO.bookRoom(6675363734328343759l, 1, 8577383723364764120l);
//        hotelDAO.cancelReservation(6675363734328343759l, 1, 8577383723364764120l);
//        hotelDAO.cancelReservation(345346346l, 1, 232345l);

        Map<String, String> map = new HashMap<>();
        map.put("price", "334");
        map.put("person", "3");
//        System.out.println(hotelDAO.findRoom(map));
        HotelController hotelController = new HotelController();


        // Тест Main Василий.Виталий
        // НАЙТИ КОМНАТУ - СПИСОК КОМНАТ
        /*1. Замечание - метод findRooms - находится в HotelController и возвращает List<Hotel>,
        а по заданию - мы должны получить List<Room>.
        2. Логично что бы этот метод находился в RoomController и возвращал List<Room>*/
        // БРОНИРОВАНИЕ КОМНАТЫ - ok - но результат в консоль в виде Exception
        /*hotelController.bookRoom(6675363734328343759L, 7613311618539299703L, 6124218799539178536L, currentDate, endDate);*/
        // НАЙТИ ГОСТИНИЦУ ПО ГОРОДУ
        /*
       При Тесте Main, при вызове метода findHotelByCity
       и передачи ему несуществующего запроса "Кие" - получаем NullPointerException
       Решение: if (result.isEmpty()) {
            throw new DataCorruptionException("There is no such City, please check city name and try again");
        }*/
        // НАЙТИ ГОСТИНИЦУ ПО ИМЕНИ
        // hotelController.findHotelByName("Radisson").forEach(System.out::println);
        /*
       При Тесте Main, при вызове метода findHotelByName
       и передачи ему несуществующего запроса "Radiss" - получаем NullPointerException
       Решение: if (result.isEmpty()) {
                throw new DataCorruptionException("There is no hotel with such name");
            }
         */
    }
}
