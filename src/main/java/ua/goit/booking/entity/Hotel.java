package ua.goit.booking.entity;

import ua.goit.booking.entity.Room;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /* public RoomDAO getRoomDAO() {
        return roomDAO;
    }*/

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", rooms=" + rooms +
                '}';
    }

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
