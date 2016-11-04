package ua.goit.booking.entity;

import java.util.Date;
import java.util.UUID;

public class Room {
    private long id;
    private int price;
    private int person;
    private boolean isBooked;
    private long userBookedId;

    public Room() { }

    public Room(long id, int price, int person, boolean isBooked, long userBookedId) {
        this.id = id;
        this.price = price;
        this.person = person;
        this.isBooked = isBooked;
        this.userBookedId = userBookedId;
    }

    public Room(int price, int person, boolean isBooked, long userBookedId) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.price = price;
        this.person = person;
        this.isBooked = isBooked;
        this.userBookedId = userBookedId;
    }

    public Room(int price, int person, boolean isBooked) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.price = price;
        this.person = person;
        this.isBooked = isBooked;
    }

    public Room() {

    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getPerson() {
        return person;
    }

    public boolean isBooked() {
        return isBooked;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", price=" + price +
                ", person=" + person +
                ", isBooked=" + isBooked +
                ", userBookedId=" + userBookedId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (price != room.price) return false;
        if (person != room.person) return false;
        if (isBooked != room.isBooked) return false;
        return userBookedId == room.userBookedId;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + price;
        result = 31 * result + person;
        result = 31 * result + (isBooked ? 1 : 0);
        result = 31 * result + (int) (userBookedId ^ (userBookedId >>> 32));
        return result;
    }
}