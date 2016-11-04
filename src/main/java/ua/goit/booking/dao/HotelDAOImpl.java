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
import ua.goit.booking.entity.Room;

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

    private void updateFile(List<Hotel> hotels) {
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
        List<Hotel> hotels = getAllHotels();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (!room.isBooked()) {
                            room.setBooked(true);
                            room.setUserBookedId(userId);
                            System.out.println("Success! " + room + " was booked!");
                            updateFile(hotels);
                            return;
                        }
                        else {
                            System.out.println("Sorry! " + room + " is already booked!");
                            return;
                        }
                    }
                }
                System.out.println("Sorry! No such room in the hotel.");
                return;
            }
        }
        System.out.println("Sorry! No such hotel.");
        return;
    }

    public void cancelReservation(long roomId, long userId, long hotelId) {
        List<Hotel> hotels = getAllHotels();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (room.isBooked()) {
                            if (room.getUserBookedId() == userId){
                                room.setBooked(false);
                                room.setUserBookedId(0l);
                                System.out.println("Success! " + room + " reservation was canceled!");
                                updateFile(hotels);
                                return;
                            }
                            else {
                                System.out.println("Sorry! You did not booked this room!");
                                return;
                            }
                        }
                        else {
                            System.out.println("Sorry! " + room + " is not booked!");
                            return;
                        }
                    }
                }
                System.out.println("Sorry! No such room in the hotel.");
                return;
            }
        }
        System.out.println("Sorry! No such hotel.");
        return;
    }

    public List<Hotel> findRoom(Map<String, String> params) {
        return null;
    }
}