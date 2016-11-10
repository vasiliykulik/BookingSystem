package ua.goit.booking.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.goit.booking.dao.Identity;

import java.lang.reflect.Field;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room implements Identity {

    private Long id;
    private int price;
    private int numberOfVisitors;
    private Long hotelId;

    public Room() {
    }

    public Room(int price, int numberOfVisitors) {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.price = price;
        this.numberOfVisitors = numberOfVisitors;
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

    public Long getHotelId() {
        return hotelId;
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

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public static Field[] getFieldsName() {
        return Room.class.getDeclaredFields();
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

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", price=" + price +
                ", numberOfVisitors=" + numberOfVisitors +
                ", hotelId=" + hotelId +
                '}';
    }

}
