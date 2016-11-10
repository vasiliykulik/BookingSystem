package ua.goit.booking.dao;

import ua.goit.booking.dao.exception.ReservationDaoException;
import ua.goit.booking.entity.Reservation;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;

import java.util.Date;

public interface ReservationDao extends AbstractDao<Reservation> {

    boolean bookRoom(long roomId, String fromDate, String toDate, long userId) throws ReservationDaoException;

    boolean cancelReservation(long roomId, String fromDate, String toDate, long userId) throws ReservationDaoException;

    boolean isFree(long roomId, Date fromDate, Date toDate) throws ReservationDaoException;
}
