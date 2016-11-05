package ua.goit.booking.entity;

import ua.goit.booking.dao.DAO;
import ua.goit.booking.dao.RoomDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Refactored by Dima on 05.11.16.
 */
public class Hotel implements Identity {
    private long id;
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
//
//    public Hotel(String hotelName, String cityName, List<Room> roomsId) {
//        long newId = UUID.randomUUID().getLeastSignificantBits();
//        if (newId <= 0) {
//            this.id = newId * -1;
//        } else {
//            this.id = newId;
//        }
//        this.hotelName = hotelName;
//        this.cityName = cityName;
//        this.roomsId = new ArrayList<>();
//        setRooms(roomsId);
//    }

    @Override
    public long getId() {
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
        List<Room> res = new ArrayList<>();
        DAO roomDAO = new RoomDAO();
        res.addAll(roomDAO.getAllById(roomsId));
        return res;
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
        this.roomsId = rooms.stream().map(Room::getId).collect(Collectors.toList());
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
                ", roomsId=" + roomsId +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != hotel.id) return false;
        if (hotelName != null ? !hotelName.equals(hotel.hotelName) : hotel.hotelName != null) return false;
        if (cityName != null ? !cityName.equals(hotel.cityName) : hotel.cityName != null) return false;
        return roomsId != null ? roomsId.equals(hotel.roomsId) : hotel.roomsId == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (roomsId != null ? roomsId.hashCode() : 0);
        return result;
    }
}
