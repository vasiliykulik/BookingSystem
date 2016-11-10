package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.dao.exception.HotelDaoException;
import ua.goit.booking.dao.exception.ReservationDaoException;
import ua.goit.booking.dao.exception.RoomDaoException;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.util.DateTime;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class RoomDaoImpl extends AbstractDaoImp<Room> implements RoomDao {

    public RoomDaoImpl() {
        super(new File("static/rooms.json"), new TypeReference<List<Room>>() {
        });
    }

    @Override
    public List<Room> findRoom(Map<String, String> params) {
        List<Room> result = getAll();
        HotelDao hotelDao = new HotelDaoImpl();

        Date fromDate = parseDate(params.get("fromDate"), "yyyy-MM-dd");
        Date toDate = parseDate(params.get("toDate"), "yyyy-MM-dd");

        Date startFromDate = DateTime.getInstance(fromDate).getStartOfDay().getDate();
        Date endToDate = DateTime.getInstance(toDate).getEndOfDay().getDate();

        System.out.println(startFromDate);
        System.out.println(endToDate);

        ReservationDao reservationDao = new ReservationDaoImpl();
        result.removeIf(room -> {
            try {
                return !reservationDao.isFree(room.getId(), startFromDate, endToDate);
            } catch (ReservationDaoException e) {
                e.printStackTrace();
                return false;
            }
        });

        if (params.containsKey("price")) {
            try {
                int price = Integer.parseInt(params.get("price"));
                result.removeIf(room -> room.getPrice() > price);
            } catch (NumberFormatException e) {
                System.out.println("Logger: NumberFormatException!");
            }
        }
        if (params.containsKey("cityName")) {
            String cityName = params.get("cityName");
            Predicate<Room> predicate = room -> {
                try {
                    return !hotelDao.getById(room.getHotelId()).getCityName().equals(cityName);
                } catch (AbstractDaoException e) {
                    e.printStackTrace();
                    return false;
                }
            };
            result.removeIf(predicate);
        }
        if (params.containsKey("hotelName")) {
            String hotelName = params.get("hotelName");
            Predicate<Room> predicate = room -> {
                try {
                    return !hotelDao.getById(room.getHotelId()).getHotelName().equals(hotelName);
                } catch (AbstractDaoException e) {
                    e.printStackTrace();
                    return false;
                }
            };
            result.removeIf(predicate);
        }
        if (params.containsKey("numberOfVisitors")) {
            try {
                int numberOfVisitors = Integer.parseInt(params.get("numberOfVisitors"));
                result.removeIf(room -> room.getNumberOfVisitors() != numberOfVisitors);
            } catch (NumberFormatException e) {
                System.out.println("Logger: NumberFormatException!");
            }
        }

        return result;
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
    public boolean delete(Room room) throws AbstractDaoException {
        HotelDao hotelDao = new HotelDaoImpl();
        Hotel hotel;
        try {
            hotel = hotelDao.getById(room.getHotelId());
            hotel.getRoomsId().removeIf(id -> id.equals(room.getId()));
            hotelDao.save(hotel);
        } catch (HotelDaoException e) {
            System.out.println("Hotel is not exist!");
        }
        super.delete(room);

        return true;
    }

    public boolean delete(List<Room> rooms) throws AbstractDaoException {
        if (rooms.isEmpty()) {
            throw new RoomDaoException("List of rooms is empty!");
        }
        rooms.forEach(room -> {
            try {
                delete(room);
            } catch (AbstractDaoException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

}
