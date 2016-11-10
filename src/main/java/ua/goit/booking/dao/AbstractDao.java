package ua.goit.booking.dao;

import ua.goit.booking.dao.exception.AbstractDaoException;

import java.util.List;

public interface AbstractDao<T> {

    List<T> getAll();

    T getById(long id) throws AbstractDaoException;

    List<T> getAllById(List<Long> ids);

    T save(T t) throws AbstractDaoException;

    boolean delete(T t) throws AbstractDaoException;

    boolean deleteById(long id) throws AbstractDaoException;

}
