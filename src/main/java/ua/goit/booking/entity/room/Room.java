package ua.goit.booking.entity.room;

import ua.goit.booking.entity.user.User;

import java.util.Date;
import java.util.List;

public class Room {
    private long id;
    private long userId;
    private int price;
    private int person;
    private String hotelName;
    private Date dateAvailable;
    private String cityName;
    private boolean isRoomBooking;

    public Room(long id, int price, int person, String hotelName,
                Date dateAvailable, String cityName) {
        this.id = id;
        this.price = price;
        this.person = person;
        this.hotelName = hotelName;
        this.dateAvailable = dateAvailable;
        this.cityName = cityName;
        this.isRoomBooking = false;
    }


    public boolean isRoomBooking() {
        return isRoomBooking;
    }

    public void setRoomBooking(boolean booking) {
        isRoomBooking = booking;
    }

    public String getCityName() {

        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getHotelName() {

        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getDateAvailable() {

        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public int getPerson() {

        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (price != room.price) return false;
        if (person != room.person) return false;
        if (hotelName != null ? !hotelName.equals(room.hotelName) : room.hotelName != null) return false;
        return cityName != null ? cityName.equals(room.cityName) : room.cityName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + price;
        result = 31 * result + person;
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }
}