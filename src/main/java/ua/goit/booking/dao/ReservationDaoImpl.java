package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.ReservationDaoException;
import ua.goit.booking.model.entity.Reservation;
import ua.goit.booking.model.entity.User;
import ua.goit.booking.util.DateTime;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationDaoImpl extends AbstractDaoImp<Reservation> implements ReservationDao {

    public ReservationDaoImpl() {
        super(new File("db/reservation.json"), new TypeReference<List<Reservation>>() {
        });
    }

    @Override
    public boolean bookRoom(long roomId, String fromDate, String toDate, long userId) throws ReservationDaoException {
        UserDao userDao = new UserDaoImpl();
        User user;
        try {
            user = userDao.getById(userId);
        } catch (AbstractDaoException e) {
            throw new ReservationDaoException("This user is not registered");
        }
        Date parsedFromDate = parseDate(fromDate, "yyyy-MM-dd");
        Date parsedToDate = parseDate(toDate, "yyyy-MM-dd");

        Date startFromDate;
        Date endToDate;
        if (parsedFromDate == null || parsedToDate == null) {
            throw new ReservationDaoException("The date is wrong");
        } else {
            startFromDate = DateTime.getInstance(parsedFromDate).getStartOfDay().getDate();
            endToDate = DateTime.getInstance(parsedToDate).getEndOfDay().getDate();
        }
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
        if (date == null || date.equals("")) {
            return null;
        }
        try {
            return DateTime.parse(date, format);
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
        reservations.removeIf(reservation -> !(startFromDate.equals(reservation.getFromDate()) && endToDate.equals(reservation.getToDate())
                && userId == reservation.getUserId()));
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
