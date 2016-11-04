import jdk.nashorn.internal.parser.JSONParser;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import java.io.File;
import java.io.FileReader;
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
        readFromJson();
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
        ObjectMapper mapper = new ObjectMapper();

        try {
            Hotel hotel = mapper.readValue(new File("/home/taras/Documents/Java/BookingSystem/static/info.json"), Hotel.class);
            System.out.println(hotel);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
