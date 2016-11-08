package ua.goit.booking.dao;

import java.util.List;

public interface AbstractDao<T> {

    List<T> getAll();

    T getById(long id);

    List<T> getAllById(List<Long> ids);

    T save(T t);

    boolean delete(T t);

    boolean update(T t);

    void updateBase(List<T> list);

    boolean isDataCorrupted(List<T> list);

    boolean isContainId(Long id);

}
