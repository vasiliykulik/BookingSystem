package ua.goit.booking.entity;

import java.util.UUID;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public User() { }

    public User(String firstName, String lastName, String emailAddress) {
        long newId = UUID.randomUUID().getLeastSignificantBits();
        if (newId <= 0) {
            this.id = newId * -1;
        } else {
            this.id = newId;
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        return emailAddress != null ? emailAddress.equals(user.emailAddress) : user.emailAddress == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }
}
