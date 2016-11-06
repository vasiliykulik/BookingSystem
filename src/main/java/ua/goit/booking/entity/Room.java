package ua.goit.booking.entity;

import ua.goit.booking.dao.Identity;
import ua.goit.booking.dao.UserDaoImpl;

import java.lang.reflect.Field;
import java.util.UUID;

public class Room implements Identity {
    private Long id;
    private int price;
    private int numberOfVisitors;
    private boolean isBooked;
    private Long userId;
    private Long hotelId;

    public Room() {}

    public Room(int price, int numberOfVisitors, boolean isBooked, Long userId, Long hotelId) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.price = price;
        this.numberOfVisitors = numberOfVisitors;
        this.isBooked = isBooked;
        this.userId = userId;
        this.hotelId = hotelId;
    }

    public Room(int price, int numberOfVisitors, boolean isBooked) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.price = price;
        this.numberOfVisitors = numberOfVisitors;
        this.isBooked = isBooked;
    }

    @Override
    public Long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public Long getUserId() {
        return userId;
    }

    public User getUser() {
        return new UserDaoImpl().getById(userId);
    }

    public Long getHotelId() {
        return hotelId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUser(User user) {
        this.userId = user.getId();
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public static Field[] getFieldsName() {
        return Room.class.getDeclaredFields();
    }

    @Override
    public String toString() {
        return "\n" + "Room{" +
                "id=" + id +
                ", price=" + price +
                ", numberOfVisitors=" + numberOfVisitors +
                ", isBooked=" + isBooked +
                ", userId=" + userId +
                ", userId=" + userId +
                '}';
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Room room = (Room) o;
//
//        if (id != room.id) return false;
//        if (price != room.price) return false;
//        if (numberOfVisitors != room.numberOfVisitors) return false;
//        if (isBooked != room.isBooked) return false;
//        return userId == room.userId;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + price;
//        result = 31 * result + numberOfVisitors;
//        result = 31 * result + (isBooked ? 1 : 0);
//        result = 31 * result + (int) (userId ^ (userId >>> 32));
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (price != room.price) return false;
        if (numberOfVisitors != room.numberOfVisitors) return false;
        if (isBooked != room.isBooked) return false;
        if (id != null ? !id.equals(room.id) : room.id != null) return false;
        if (userId != null ? !userId.equals(room.userId) : room.userId != null) return false;
        return hotelId != null ? hotelId.equals(room.hotelId) : room.hotelId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + numberOfVisitors;
        result = 31 * result + (isBooked ? 1 : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (hotelId != null ? hotelId.hashCode() : 0);
        return result;
    }
}