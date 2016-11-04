package ua.goit.booking.entity;

import java.util.Date;
import java.util.UUID;

public class Room {
    private long id;
    private int price;
    private int person;
    private boolean isBooked;
    private User userBooked;

    public Room(int price, int person, boolean isBooked, User userBooked) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.price = price;
        this.person = person;
        this.isBooked = isBooked;
        this.userBooked = userBooked;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (price != room.price) return false;
        if (person != room.person) return false;
        if (isBooked != room.isBooked) return false;
        return userBooked != null ? userBooked.equals(room.userBooked) : room.userBooked == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + price;
        result = 31 * result + person;
        result = 31 * result + (isBooked ? 1 : 0);
        result = 31 * result + (userBooked != null ? userBooked.hashCode() : 0);
        return result;
    }
}