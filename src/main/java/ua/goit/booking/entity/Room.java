package ua.goit.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.goit.booking.dao.Identity;
import ua.goit.booking.dao.UserDaoImpl;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.util.DateTime;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room implements Identity {
    private Long id;
    private int price;
    private int numberOfVisitors;
//    private Date fromDate;
//    private Date toDate;
    private Long userId;
    private Long hotelId;

    public Room() {
    }

    public Room(int price, int numberOfVisitors) {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.price = price;
        this.numberOfVisitors = numberOfVisitors;
//        this.fromDate = DateTime.getInstance().getYesterdaysDate().getEndOfDay().getDate();
//        this.toDate = this.fromDate;
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

    @JsonIgnore
    public User getUser() throws AbstractDaoException {
        return new UserDaoImpl().getById(userId);
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

//    @JsonIgnore
//    public boolean isFree(Date fromDate, Date toDate) {
//        if (fromDate == null && toDate == null) {
//            return false;
//        } else if (fromDate == null) {
//            return toDate.before(this.fromDate) || toDate.after(this.toDate);
//        } else if (toDate == null) {
//            return fromDate.before(this.fromDate) || fromDate.after(this.toDate);
//        }
//        return toDate.before(this.fromDate) || fromDate.after(this.toDate);
//    }

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
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                '}';
    }
}
