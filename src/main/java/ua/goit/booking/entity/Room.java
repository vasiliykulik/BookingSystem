package ua.goit.booking.entity.room;

import java.util.Date;

public class Room {
    private long id;
    private int price;
    private int person;
    private boolean isBooked;
    private long userBookedId;

    public Room(long id, int price, int person, boolean isBooked, long userBookedId) {
        this.id = id;
        this.price = price;
        this.person = person;
        this.isBooked = isBooked;
        this.userBookedId = userBookedId;
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

    public long getUserBookedId() {
        return userBookedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return id == room.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}