package ua.goit.booking;

import ua.goit.booking.dao.HotelDAOImpl;

public class Main {

    public static void main(String[] args) {
        HotelDAOImpl hotelDAO = new HotelDAOImpl();
//        hotelDAO.findHotelByName("Plaza").forEach(System.out::println);
//        hotelDAO.findHotelDyCity("Kiyv").forEach(System.out::println);
//        hotelDAO.bookRoom(6675363734328343759l, 1, 8577383723364764120l);
//        hotelDAO.cancelReservation(6675363734328343759l, 1, 8577383723364764120l);
        hotelDAO.cancelReservation(345346346l, 1, 232345l);

    }
}
