package ua.goit.booking.dao;

import java.util.List;

/**
 * Created by Dima on 05.11.16.
 */
public interface DAO<T> {

    List<T> getAll();

    void update(List<T> hotels);
}
