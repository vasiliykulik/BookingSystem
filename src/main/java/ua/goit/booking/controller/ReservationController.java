package ua.goit.booking.controller;

import ua.goit.booking.dao.ReservationDao;
import ua.goit.booking.dao.ReservationDaoImpl;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.ReservationDaoException;
import ua.goit.booking.model.entity.Reservation;
import ua.goit.booking.model.entity.Room;

import java.util.List;

public class ReservationController {

    private static ReservationDao reservationDao = new ReservationDaoImpl();

    public boolean bookRoom(long roomId, String fromDate, String toDate, long userId) {
        try {
            return reservationDao.bookRoom(roomId, fromDate, toDate, userId);
        } catch (ReservationDaoException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reservation> getAll() {
        return reservationDao.getAll();
    }

    public boolean cancelReservation(long roomId, String fromDate, String toDate, long userId) {
        try {
            return reservationDao.cancelReservation(roomId, fromDate, toDate, userId);
        } catch (ReservationDaoException e) {
            e.printStackTrace();
            return false;
        }
    }

}
