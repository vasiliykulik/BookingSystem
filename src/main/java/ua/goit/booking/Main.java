package ua.goit.booking;

import ua.goit.booking.controller.HotelController;
import ua.goit.booking.controller.ReservationController;
import ua.goit.booking.controller.RoomController;
import ua.goit.booking.controller.UserController;
import ua.goit.booking.controller.exception.HotelControllerException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;
import ua.goit.booking.util.DateTime;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        HotelController hotelController = new HotelController();
        RoomController roomController = new RoomController();
        UserController userController = new UserController();
        ReservationController reservationController = new ReservationController();

        //Find Hotel by name
        System.out.println("Find all hotels with name Radisson:");
        hotelController.findHotelByName("Radisson").forEach(System.out::println);
        System.out.println("_______________");
        System.out.println("Find all hotels with name Test:");
        hotelController.findHotelByName("Test").forEach(System.out::println);
        System.out.println("_______________");
        System.out.println("Find all hotels with empty name:");
        hotelController.findHotelByName("").forEach(System.out::println);
        System.out.println("======================================\n");

        //Find Hotel by city
        System.out.println("Find all hotels with city Kiev:");
        hotelController.findHotelByCity("Kiev").forEach(System.out::println);
        System.out.println("_______________");
        System.out.println("Find all hotels with city Test:");
        hotelController.findHotelByCity("Test").forEach(System.out::println);
        System.out.println("_______________");
        System.out.println("Find all hotels with empty city:");
        hotelController.findHotelByCity("").forEach(System.out::println);
        System.out.println("======================================");

        //Book room
        Random random = new Random();

        List<Room> allRooms = roomController.getAll();
        List<User> allUsers = userController.getAll();

        long randomRoomId = allRooms.get(random.nextInt(allRooms.size())).getId();
        long randomUserId = allUsers.get(random.nextInt(allUsers.size())).getId();

        String fromDate = DateTime.getInstance().plusDays(2).toString("yyyy-mm-dd");
        String toDate = DateTime.getInstance().plusDays(2).toString("yyyy-mm-dd");

        System.out.println(String.format("Book room by parametes: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s", randomRoomId, fromDate, toDate, randomUserId));
//        reservationController.bookRoom(randomRoomId, fromDate, toDate, randomUserId);
        reservationController.getAll().forEach(System.out::println);
        System.out.println("_______________");
        String pastDate = DateTime.getInstance().minusDays(1).toString("yyyy-mm-dd");
        System.out.println(String.format("Book room by wrong parametes: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s", 1231241234L, pastDate, pastDate, 234234234234L));
        reservationController.bookRoom(1231241234L, pastDate, pastDate, 12312341234L);
        reservationController.getAll().forEach(System.out::println);
        System.out.println("_______________");


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
