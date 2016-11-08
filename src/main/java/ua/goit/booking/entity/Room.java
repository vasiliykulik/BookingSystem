package ua.goit.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.goit.booking.dao.*;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room implements Identity {
    private Long id;
    private int price;
    private int numberOfVisitors;
    private Date fromDate;
    private Date toDate;
    private Long userId;
    private Long hotelId;

    public Room() {
    }

    public Room(int price, int numberOfVisitors, Long hotelId) {
        HotelDao hotelDao = new HotelDaoImpl();
        if (!hotelDao.isContainId(hotelId)) {
            //TODO Exception
            System.out.println("Hotel with this ID does not exist");
            return;
        }

        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.price = price;
        this.numberOfVisitors = numberOfVisitors;
        this.fromDate = Calendar.getInstance().getTime();
        this.toDate = Calendar.getInstance().getTime();
        this.hotelId = hotelId;
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
    public User getUser() {
        return new UserDaoImpl().getById(userId);
    }

    public Long getHotelId() {
        return hotelId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUser(User user) {
        this.userId = user.getId();
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public static Field[] getFieldsName() {
        return Room.class.getDeclaredFields();
    }

    @JsonIgnore
    public boolean isBooked(Date fromDate, Date toDate) {
        return this.toDate.after(toDate) && this.fromDate.before(fromDate);
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
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                '}';
    }
}
