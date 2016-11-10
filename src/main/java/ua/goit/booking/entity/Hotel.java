package ua.goit.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.goit.booking.dao.Identity;
import ua.goit.booking.dao.RoomDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hotel implements Identity {

    private Long id;
    private String hotelName;
    private String cityName;
    private List<Long> roomsId;

    public Hotel() {
    }

    public Hotel(String hotelName, String cityName) {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.roomsId = new ArrayList<>();
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

    @JsonIgnore
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

    public void setRoomsId(List<Long> roomsId) {
        this.roomsId = roomsId;
    }

    public void setRooms(List<Room> rooms) {
        this.roomsId = rooms.stream().map(Room::getId).collect(Collectors.toList());
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

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", roomsId=" + roomsId +
                '}';
    }

}
