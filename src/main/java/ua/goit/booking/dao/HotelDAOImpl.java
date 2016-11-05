package ua.goit.booking.dao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;

/**
 * Created by taras on 04.11.16.
 */
public class HotelDAOImpl implements HotelDAO {

    @Override
    public List<Hotel> getAllHotels() {
        ObjectMapper mapper = new ObjectMapper();
        List<Hotel> hotels = new ArrayList<>();
        try {
            hotels = mapper.readValue(new File("static/info.json"), new TypeReference<ArrayList<Hotel>>(){});
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    @Override
    public void update(List<Hotel> hotels) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("static/info.json"), hotels);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}