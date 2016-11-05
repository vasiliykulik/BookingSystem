package ua.goit.booking.dao;

import java.util.List;

/**
 * Created by taras on 04.11.16.
 * Refactored by Dima on 05.11.16.
 */
public interface DAO<T> {

    List<T> getAll();

    T getById(long id);

    List<T> getAllById(List<Long> ids);

    void update(List<T> list);

}
