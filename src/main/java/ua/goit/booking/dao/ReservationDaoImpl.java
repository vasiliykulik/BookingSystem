package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.controller.exception.ReservationControllerException;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.ReservationDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Reservation;
import ua.goit.booking.entity.Room;
import ua.goit.booking.entity.User;
import ua.goit.booking.util.DateTime;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDaoImpl extends AbstractDaoImp<Reservation> implements ReservationDao {

    public ReservationDaoImpl() {
        super(new File("static/reservation.json"), new TypeReference<List<Reservation>>() {
        });
    }

    @Override
    public boolean bookRoom(long roomId, String fromDate, String toDate, long userId) throws ReservationDaoException {
        UserDao userDao = new UserDaoImpl();
        User user;
        try {
            user = userDao.getById(userId);
        } catch (AbstractDaoException e) {
            throw new ReservationDaoException("This user is note registered");
        }

        Date startFromDate = DateTime.getInstance(parseDate(fromDate, "yyyy-MM-dd")).getStartOfDay().getDate();
        Date endToDate = DateTime.getInstance(parseDate(toDate, "yyyy-MM-dd")).getEndOfDay().getDate();

        if (!isFree(roomId, startFromDate, endToDate)) {
            throw new ReservationDaoException("Room is booked");
        }

        Reservation reservation = new Reservation(roomId, userId, startFromDate, endToDate);
        try {
            save(reservation);
        } catch (AbstractDaoException e) {
            throw new ReservationDaoException(e.getMessage());
        }
        return true;
    }

    private Date parseDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isFree(long roomId, Date fromDate, Date toDate) throws ReservationDaoException {
        if (roomId == 0) {
            throw new ReservationDaoException("You cannot reserve this room");
        }
        RoomDao roomDao = new RoomDaoImpl();
        try {
            roomDao.getById(roomId);
        } catch (AbstractDaoException e) {
            throw new ReservationDaoException("Room is not found");
        }
        List<Reservation> reservations = allReservationsByRoom(roomId);

        Date currentDate = DateTime.getInstance().getTodaysDate().getStartOfDay().getDate();
        if ((fromDate == null && toDate == null) ||
                fromDate == null || toDate == null ||
                fromDate.before(currentDate) || toDate.before(currentDate)) {
            throw new ReservationDaoException("Incorrect Date for reservation");
        }

        //TODO check on swapped Date

        return reservations.stream()
                .allMatch(reservation -> toDate.before(reservation.getFromDate()) || fromDate.after(reservation.getToDate()));
    }

    private List<Reservation> allReservationsByRoom(long roomId) {
        return getAll().stream()
                .filter(reservation -> reservation.getRoomId() == roomId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean cancelReservation(long roomId, String fromDate, String toDate, long userId) throws ReservationDaoException {
        if (roomId == 0 || userId == 0 ||
                fromDate == null || toDate == null ||
                fromDate.equals("") || toDate.equals("")) {
            throw new ReservationDaoException("Incorrect request for cancel reservation");
        }
        Date startFromDate = DateTime.getInstance(parseDate(fromDate, "yyyy-MM-dd")).getStartOfDay().getDate();
        Date endToDate = DateTime.getInstance(parseDate(toDate, "yyyy-MM-dd")).getEndOfDay().getDate();

        List<Reservation> reservations = allReservationsByRoom(roomId);
        reservations.removeIf(reservation -> !(startFromDate.equals(reservation.getFromDate()) && endToDate.equals(reservation.getToDate()))
                && userId != reservation.getUserId());
        if (reservations.isEmpty()) {
            throw new ReservationDaoException("This reservation has not been found!");
        }
        try {
            return delete(reservations.get(0));
        } catch (AbstractDaoException e) {
            e.printStackTrace();
            return false;
        }
    }
}
