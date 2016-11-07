package ua.goit.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.goit.booking.dao.Identity;
import ua.goit.booking.dao.UserDaoImpl;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
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
//        long newId = UUID.randomUUID().getLeastSignificantBits();
//        if (newId <= 0) {
//            this.id = newId * -1;
//        } else {
//            this.id = newId;
//        }
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.price = price;
        this.numberOfVisitors = numberOfVisitors;
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
    public boolean isBooked() {
        Date currentDate = Calendar.getInstance().getTime();
        return toDate.after(currentDate) && fromDate.before(currentDate);
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
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", numberOfVisitors=").append(numberOfVisitors);
        sb.append(", fromDate=").append(fromDate);
        sb.append(", toDate=").append(toDate);
        sb.append(", userId=").append(userId);
        sb.append(", hotelId=").append(hotelId);
        sb.append('}');
        return sb.toString();
    }
}
