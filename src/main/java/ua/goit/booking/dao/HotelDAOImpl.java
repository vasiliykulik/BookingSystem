package ua.goit.booking.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.entity.Hotel;

/**
 * Created by taras on 04.11.16.
 */
public class HotelDAOImpl implements HotelDAO {

    private List<Hotel> getAllHotels() {
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

    public List<Hotel> findHotelByName(String name) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : getAllHotels()) {
            if (hotel.getHotelName().equals(name)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public List<Hotel> findHotelDyCity(String city) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : getAllHotels()) {
            if (hotel.getCityName().equals(city)) {
                result.add(hotel);
            }
        }
        return result;
    }

    public void bookRoom(long roomId, long userId, long hotelId) {

    }

    public void cancelReservation(long roomId, long userId, long hotelId) {

    }

    public List<Hotel> findRoom(Map<String, String> params) {
        return null;
    }
}