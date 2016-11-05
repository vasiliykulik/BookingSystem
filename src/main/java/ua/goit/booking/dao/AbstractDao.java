package ua.goit.booking.dao;

import java.util.List;

/**
 * Created by taras on 04.11.16.
 * Refactored by Dima on 05.11.16.
 */
public interface AbstractDao<T> {

    List<T> getAll();

    T getById(long id);

    List<T> getAllById(List<Long> ids);

    T save(T t);

    void delete(T t);

    void update(List<T> list);

}
