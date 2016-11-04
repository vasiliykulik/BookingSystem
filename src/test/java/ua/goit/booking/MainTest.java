package ua.goit.booking;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.dao.HotelDAOImpl;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by taras on 03.11.16.
 */
public class MainTest {

    public static void main(String[] args) {
        HotelDAOImpl hotelDAO = new HotelDAOImpl();
//        hotelDAO.findHotelByName("Plaza").forEach(System.out::println);
//        hotelDAO.findHotelDyCity("Kiyv").forEach(System.out::println);
//        hotelDAO.bookRoom(6675363734328343759l, 1, 8577383723364764120l);
//        hotelDAO.cancelReservation(6675363734328343759l, 1, 8577383723364764120l);
        hotelDAO.cancelReservation(345346346l, 1, 232345l);


    }

    private static void fillFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<Hotel> hotels = new ArrayList<>();
        List<ArrayList<Room>> roomList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            roomList.add(new ArrayList<Room>());
            String cityName = "";
            String hotelName = "";
            for (int j = 0; j < 5; j++) {
                int price = (int)(Math.random()*300+200);
                int person = (int)(Math.random()*3+1);
                roomList.get(i).add(new Room (price, person, false, 0l));
                switch (i) {
                    case 0: cityName = "Kiyv";
                        break;
                    case 1: cityName = "Odessa";
                        break;
                    case 2: cityName = "Lviv";
                        break;
                    case 3: cityName = "Dnepr";
                        break;
                    case 4: cityName = "Rivne";
                        break;
                }
                switch (j) {
                    case 0: hotelName = "Radisson";
                        break;
                    case 1: hotelName = "Plaza";
                        break;
                    case 2: hotelName = "Hilton";
                        break;
                    case 3: hotelName = "Ukraine";
                        break;
                    case 4: hotelName = "Luxury";
                        break;
                }
                hotels.add(new Hotel(hotelName, cityName, roomList.get(i)));
            }
        }

        try {
            mapper.writeValue(new File("static/info.json"), hotels);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
