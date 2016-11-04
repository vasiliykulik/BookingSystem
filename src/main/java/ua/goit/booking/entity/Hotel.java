package ua.goit.booking.entity;

import ua.goit.booking.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Hotel {
    private long id;
    private String hotelName;
    private String cityName;
    private List<Room> rooms;
    // private DAO dao

    public Hotel(String hotelName, String cityName, List<Room> rooms) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
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

        if (id != hotel.id) return false;
        if (hotelName != null ? !hotelName.equals(hotel.hotelName) : hotel.hotelName != null) return false;
        if (cityName != null ? !cityName.equals(hotel.cityName) : hotel.cityName != null) return false;
        return rooms != null ? rooms.equals(hotel.rooms) : hotel.rooms == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        return result;
    }
}
