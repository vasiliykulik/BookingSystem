package ua.goit.booking.entity.hotel;

import ua.goit.booking.entity.room.Room;
import java.util.ArrayList;
import java.util.List;


public class Hotel {
    private long id;
    private String hotelName;
    private String cityName;
    private List<Room> rooms;
    // private DAO dao

    public Hotel(long id, String hotelName, String cityName, List<Room> rooms) {
        this.id = id;
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    /* public RoomDAO getRoomDAO() {
        return roomDAO;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return id == hotel.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
