package ua.goit.booking.controller.exception;

import ua.goit.booking.dao.exception.AbstractDaoException;

public class HotelControllerException extends Throwable {

    public HotelControllerException(String message) {
        super(message);
    }
}
