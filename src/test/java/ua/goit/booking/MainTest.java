package ua.goit.booking;

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

        hotelDAO.findHotelByName("Plaza").forEach(System.out::println);
        hotelDAO.findHotelDyCity("Rivne").forEach(System.out::println);

    }
}
