package ua.goit.booking.entity;

import ua.goit.booking.dao.Identity;
import ua.goit.booking.dao.RoomDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Refactored by Dima on 05.11.16.
 */
public class Hotel implements Identity {
    private Long id;
    private String hotelName;
    private String cityName;
    private List<Long> roomsId;
    // private DAO dao

    public Hotel() {

    }

    public Hotel(String hotelName, String cityName) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.roomsId = new ArrayList<>();
    }

    public Hotel(String hotelName, String cityName, List<Room> roomsId) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.roomsId = new ArrayList<>();
        setRooms(roomsId);
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public List<Long> getRoomsId() {
        return roomsId;
    }

    public List<Room> getRooms() {
        return new RoomDaoImpl().getAllById(roomsId);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setRooms(List<Room> rooms) {
        this.roomsId = rooms.stream().map(Room::getId).collect(Collectors.toList());
    }

    public void addRoom(Room room) {
        //TODO
    }

    /* public RoomDAO getRoomDAO() {
        return roomDAO;
    }*/

    @Override
    public String toString() {
        return "\n" + "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", roomsId=" + roomsId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return id != null ? id.equals(hotel.id) : hotel.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
