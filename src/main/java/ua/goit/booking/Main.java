package ua.goit.booking;

import ua.goit.booking.dao.HotelDAO;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
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
