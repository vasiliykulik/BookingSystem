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

    public Room() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return id != null ? id.equals(room.id) : room.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}