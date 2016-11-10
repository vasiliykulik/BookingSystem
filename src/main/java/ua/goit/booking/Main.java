package ua.goit.booking;

import ua.goit.booking.controller.HotelController;
import ua.goit.booking.controller.ReservationController;
import ua.goit.booking.controller.RoomController;
import ua.goit.booking.controller.UserController;
import ua.goit.booking.model.entity.Reservation;
import ua.goit.booking.model.entity.Room;
import ua.goit.booking.model.entity.User;
import ua.goit.booking.util.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        hotelController.findHotelByName(null).forEach(System.out::println);
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
        long randomRoomId = allRooms.get(random.nextInt(allRooms.size())).getId();
        List<User> allUsers = userController.getAll();
        long randomUserId = allUsers.get(random.nextInt(allUsers.size())).getId();

        String fromDate = DateTime.getInstance().plusDays(2).toString("yyyy-MM-dd");
        String toDate = DateTime.getInstance().plusDays(2).toString("yyyy-MM-dd");

        System.out.println(String.format("_______________\n" +
                        "Book room by parameters: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomRoomId, fromDate, toDate, randomUserId));
        reservationController.bookRoom(randomRoomId, fromDate, toDate, randomUserId);
        reservationController.getAll().forEach(System.out::println);

        System.out.println(String.format("_______________\n" +
                        "Book already booked room: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomRoomId, fromDate, toDate, randomUserId));
        reservationController.bookRoom(randomRoomId, fromDate, toDate, randomUserId);
        reservationController.getAll().forEach(System.out::println);

        String pastDate = DateTime.getInstance().minusDays(1).toString("yyyy-MM-dd");
        System.out.println(String.format("_______________\n" +
                        "Book room by wrong date: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomRoomId, pastDate, pastDate, randomUserId));
        reservationController.bookRoom(randomRoomId, pastDate, pastDate, randomUserId);
        reservationController.getAll().forEach(System.out::println);

        System.out.println(String.format("_______________\n" +
                        "Book room by wrong userId: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomRoomId, fromDate, toDate, 111111111111L));
        reservationController.bookRoom(randomRoomId, fromDate, toDate, 111111111111L);
        reservationController.getAll().forEach(System.out::println);

        System.out.println(String.format("_______________\n" +
                        "Book room by wrong roomId: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                111111111111L, fromDate, toDate, randomUserId));
        reservationController.bookRoom(111111111111L, fromDate, toDate, randomUserId);
        reservationController.getAll().forEach(System.out::println);
        System.out.println("======================================");


        //Cancel Reservation
        Reservation randomReservation = reservationController.getAll().stream().findAny().get();
        long randomReservationRoomId = randomReservation.getRoomId();
        String randomReservedFromDate = DateTime.getInstance(randomReservation.getFromDate()).toString("yyyy-MM-dd");
        String randomReservedToDate = DateTime.getInstance(randomReservation.getToDate()).toString("yyyy-MM-dd");
        long randomReservationUserId = randomReservation.getUserId();

        System.out.println(String.format("_______________\n" +
                        "Cancel reservation %s by parameters: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomReservation.getId(), randomReservationRoomId, randomReservedFromDate, randomReservedToDate, randomReservationUserId));
        reservationController.cancelReservation(randomReservationRoomId, randomReservedFromDate, randomReservedToDate, randomReservationUserId);
        reservationController.getAll().forEach(System.out::println);

        System.out.println(String.format("_______________\n" +
                        "Cancel reservation %s by the same parameters: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomReservation.getId(), randomReservationRoomId, randomReservedFromDate, randomReservedToDate, randomReservationUserId));
        reservationController.cancelReservation(randomReservationRoomId, randomReservedFromDate, randomReservedToDate, randomReservationUserId);
        reservationController.getAll().forEach(System.out::println);

        Reservation randomReservation2 = reservationController.getAll().stream().findAny().get();
        long randomReservationRoomId2 = randomReservation2.getRoomId();
        long randomReservationUserId2 = randomReservation2.getUserId();
        System.out.println(String.format("_______________\n" +
                        "Cancel reservation by wrong date: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomReservationRoomId2, "2016-12-01", "2016-12-01", randomReservationUserId2));
        reservationController.cancelReservation(randomReservationRoomId2, "2016-12-01", "2016-12-01", randomReservationUserId2);
        reservationController.getAll().forEach(System.out::println);

        Reservation randomReservation3 = reservationController.getAll().stream().findAny().get();
        long randomReservationRoomId3 = randomReservation3.getRoomId();
        String randomReservedFromDate3 = DateTime.getInstance(randomReservation3.getFromDate()).toString("yyyy-MM-dd");
        String randomReservedToDate3 = DateTime.getInstance(randomReservation3.getToDate()).toString("yyyy-MM-dd");
        System.out.println(String.format("_______________\n" +
                        "Cancel reservation by wrong userId: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                randomReservationRoomId3, randomReservedFromDate3, randomReservedToDate3, 111111111111L));
        reservationController.cancelReservation(randomReservationRoomId3, randomReservedFromDate3, randomReservedToDate3, 111111111111L);
        reservationController.getAll().forEach(System.out::println);

        Reservation randomReservation4 = reservationController.getAll().stream().findAny().get();
        String randomReservedFromDate4 = DateTime.getInstance(randomReservation4.getFromDate()).toString("yyyy-MM-dd");
        String randomReservedToDate4 = DateTime.getInstance(randomReservation4.getToDate()).toString("yyyy-MM-dd");
        long randomReservationUserId4 = randomReservation4.getUserId();
        System.out.println(String.format("_______________\n" +
                        "Cancel reservation by wrong roomId: RoomId = %s, FromDate = %s, ToDate = %s, UserId = %s",
                111111111111L, randomReservedFromDate4, randomReservedToDate4, randomReservationUserId4));
        reservationController.cancelReservation(111111111111L, randomReservedFromDate4, randomReservedToDate4, randomReservationUserId4);
        reservationController.getAll().forEach(System.out::println);
        System.out.println("======================================");


        //Find Room
        Map<String, String> params = new HashMap<>();
//        params.put("Country", "Ukraine");
        params.put("price", "300");
//        params.put("hotelName", "Test");
        params.put("numberOfVisitors", "3");
        params.put("fromDate", "2016-11-09");
        params.put("toDate", "2016-11-09");

        System.out.println(String.format("_______________\n" +
                "Find Room reservation by parameters: " + params));
        roomController.findRooms(params).forEach(System.out::println);

    }

}
