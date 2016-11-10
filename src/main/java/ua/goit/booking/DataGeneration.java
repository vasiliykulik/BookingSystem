package ua.goit.booking;

import sun.security.x509.AttributeNameEnumeration;
import ua.goit.booking.controller.HotelController;
import ua.goit.booking.controller.RoomController;
import ua.goit.booking.controller.exception.HotelControllerException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

import java.util.*;

public class DataGeneration {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>();
        List<String> hotels = new ArrayList<>();

        HotelController hotelController = new HotelController();

        cities.add("Kiev");
        cities.add("Lvov");
        cities.add("Odessa");

        hotels.add("Radisson");
        hotels.add("Hilton");

        Random random = new Random();
        List<Integer> visitors = Arrays.asList(1, 2, 3);

        List<Integer> prices = Arrays.asList(250, 200, 300, 450, 500, 100);

        cities.forEach(city -> hotels.forEach(hotel -> {
            Hotel newHotel = new Hotel(hotel, city);
            try {
                hotelController.save(newHotel);
            } catch (HotelControllerException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                try {
                    Integer price = prices.get(random.nextInt(prices.size()));
                    Integer numberOfVisitors = visitors.get(random.nextInt(visitors.size()));
                    hotelController.addRoom(newHotel, new Room(price, numberOfVisitors));
                } catch (HotelControllerException e) {
                    e.printStackTrace();
                }
            }
        }));


    }
}