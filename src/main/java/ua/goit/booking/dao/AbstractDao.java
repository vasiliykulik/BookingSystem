package ua.goit.booking.dao;

import java.util.List;

public interface AbstractDao<T> {

    List<T> getAll();

    T getById(long id);

    List<T> getAllById(List<Long> ids);

    T save(T t);

    void delete(T t);

    void update(List<T> list);

    boolean isDataCorrupted(List<T> list);

}
