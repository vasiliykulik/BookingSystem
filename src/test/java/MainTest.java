import ua.goit.booking.entity.hotel.Hotel;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.entity.room.Room;

/**
 * Created by taras on 03.11.16.
 */
public class MainTest {

    public static void main(String[] args) {

    }

    private static void writeInJson() {
        ObjectMapper mapper = new ObjectMapper();

        List<Room> rooms = new ArrayList<Room>();
        rooms.add(new Room(1l, 100, 2, false, 0));
        rooms.add(new Room(2l, 120, 3, false, 0));

        Hotel hotel = new Hotel(1l, "Radison", "Kiyv", rooms);

        try {
            mapper.writeValue(new File("/home/taras/Documents/Java/BookingSystem/static/info.json"), hotel);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFromJson() {

    }

}
