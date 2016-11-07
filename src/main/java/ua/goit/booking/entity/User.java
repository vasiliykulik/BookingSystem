package ua.goit.booking.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.goit.booking.dao.Identity;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Identity {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public User() {
    }

    public User(String firstName, String lastName, String emailAddress) {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    @Override
    public Long getId() {
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

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
