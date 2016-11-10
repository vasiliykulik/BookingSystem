package ua.goit.booking;

import ua.goit.booking.controller.HotelController;
import ua.goit.booking.controller.ReservationController;
import ua.goit.booking.controller.RoomController;
import ua.goit.booking.controller.UserController;
import ua.goit.booking.controller.exception.HotelControllerException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        HotelController hotelController = new HotelController();
        RoomController roomController = new RoomController();
        UserController userController = new UserController();

        //Creating new Hotel
//        hotelController.getAll().forEach(System.out::println);
//        System.out.println("________");
//        try {
//            addNewHotel("Test", "Test");
//            addNewHotel("Redisson", "Kiev");
//            addNewHotel("Redisson", "Kharkov");
//        } catch (HotelControllerException e) {
//            e.printStackTrace();
//        }
        hotelController.getAll().forEach(System.out::println);
        System.out.println(hotelController.getAll().size());
        roomController.getAll().forEach(System.out::println);
        System.out.println(roomController.getAll().size());
        System.out.println("==================");


         //Add room to the Hotel
//        try {
//            Hotel hotel = hotelController.findBy("Test", "Test");
//            Room room = new Room(1500, 2);
//            addRoomToTheHotel(hotel, room);
//        } catch (HotelControllerException e) {
//            e.printStackTrace();
//        }
//        hotelController.deleteById(6456234449482806504L);
//        hotelController.getAll().forEach(System.out::println);
//        System.out.println("==================");


        //Get all Rooms from The hotel
//        try {
//            Hotel myHotel = hotelController.findBy("Test", "Test");
//            myHotel.getRooms();
//        } catch (HotelControllerException e) {
//            e.printStackTrace();
//        }
//        System.out.println("==================");


        //Creating new Room
//        roomController.deleteById(6130684376879051181L);
//        roomController.getAll().forEach(System.out::println);
//        Room room = new Room(450, 3);
//        try {
//            roomController.save(room);
//        } catch (RoomControllerExeption roomControllerExeption) {
//            roomControllerExeption.printStackTrace();
//        }


//        User user = new User("Taras", "Lavrenyuk", "email@gmail.com");
//        userController.save(user);
//        System.out.println("==================");
//        userController.getAll().forEach(System.out::println);
//
//        Map<String, String> params = new HashMap<>();
//        params.put("cityName", "Kiev");
//        params.put("price", "2000");
//        params.put("hotelName", "Redisson");
//        params.put("numberOfVisitors", "2");
//        params.put("fromDate", "2016-11-20");
//        params.put("toDate", "2016-11-20");

//        System.out.println("================");

        //Recervation Room
//        ReservationController reservationController = new ReservationController();
//        roomController.findRooms(params).forEach(System.out::println);
//            reservationController.cancelReservation(8754584762758627686L, "2016-11-13", "2016-11-13", 7883404493335980445L);
//            reservationController.bookRoom(8754584762758627686L, "2016-11-14", "2016-11-19", 7883404493335980445L);
//            reservationController.bookRoom(8754584762758627686L, "2016-11-14", "2016-11-15", 9005465349530730763L);

//        System.out.println("================");
//        reservationController.getAll().forEach(System.out::println);


//        Hotel hotel = hotelController.getAll().stream().filter(h -> h.getHotelName().equals("Test")).findFirst().get();
//
//        Room room1 = new Room(350, 3);
//        try {
//            hotelController.addRoom(hotel, room1);
//        } catch (HotelControllerException e) {
//            e.printStackTrace();
//        }


//        List<Room> rooms = new ArrayList<>();
//        rooms.save(new Room(350, 3))

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
//        users.save(user);
//        userDao.updateBase(users); //
//        userDao.getAll().forEach(System.out::println);

//        UserController userController = new UserController();
//        userController.getAll().forEach(System.out::println);


//        Date currentDate = Calendar.getInstance().getTime();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2016, Calendar.NOVEMBER, 15, 10, 00);
//        Date endDate = calendar.getTime();

 /*       RoomController roomController = new RoomController();
//        List<Room> rooms = roomController.getAll();
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

//        Map<String, String> map = new HashMap<>();
//        map.put("price", "334");
//        map.put("person", "3");
//        System.out.println(hotelDAO.findRoom(map));


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

    private static Hotel addRoomToTheHotel(Hotel hotel, Room room) throws HotelControllerException {
        HotelController hotelController = new HotelController();
        hotelController.addRoom(hotel, room);
        return hotelController.getById(hotel.getId());
    }

    private static Hotel addNewHotel(String hotelName, String city) throws HotelControllerException {
        HotelController hotelController = new HotelController();
        Hotel hotel = new Hotel(hotelName, city);
        try {
            hotelController.save(hotel);
        } catch (HotelControllerException e) {
            e.printStackTrace();
        }
        return hotelController.getById(hotel.getId());
    }
}
