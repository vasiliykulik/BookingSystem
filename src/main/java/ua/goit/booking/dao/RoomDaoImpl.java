package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.entity.Room;
import ua.goit.booking.util.DateTime;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RoomDaoImpl extends AbstractDaoImp<Room> implements RoomDao {

    public RoomDaoImpl() {
        super(new File("static/rooms.json"), new TypeReference<List<Room>>() {
        });
    }

    @Override
    public boolean bookRoom(long roomId, String fromDate, String toDate, long userId) {
        Room room;
        try {
            room = getById(roomId);
        } catch (AbstractDaoException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(room);

        Date startFromDate = DateTime.getInstance(parseDate(fromDate, "yyyy-MM-dd")).getStartOfDay().getDate();
        Date endToDate = DateTime.getInstance(parseDate(toDate, "yyyy-MM-dd")).getEndOfDay().getDate();

        if (room.isBooked(startFromDate, endToDate)) {
            return false;
        }

        room.setFromDate(startFromDate);
        room.setToDate(endToDate);
        room.setUserId(userId);

        try {
            save(room);
        } catch (AbstractDaoException e) {
            e.printStackTrace();
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
    public List<Room> findRoom(Map<String, String> params) {
        List<Room> result = getAll();
        HotelDao hotelDao = new HotelDaoImpl();

        Date fromDate = parseDate(params.get("fromDate"), "yyyy-MM-dd");
        Date toDate = parseDate(params.get("toDate"), "yyyy-MM-dd");

        Date startFromDate = DateTime.getInstance(fromDate).getStartOfDay().getDate();
        Date endToDate = DateTime.getInstance(toDate).getEndOfDay().getDate();

        System.out.println(startFromDate);
        System.out.println(endToDate);

        result.removeIf(room -> {
            boolean booked = room.isBooked(startFromDate, endToDate);
            System.out.println(booked);
            return booked;
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

    private <T> Map<T, List<Room>> separateList(List<Room> rooms, Function<Room, T> function) {
        return rooms.stream()
                    .collect(Collectors.groupingBy(function));
    }

    @Override
    public boolean delete(Room room) {
       /* HotelDao hotelDao = new HotelDaoImpl();
        List<Room> allRooms = null;
        List<Room> roomList = getAll();
        try {
            if (isDataCorrupted(roomList)) {
                throw new DataCorruptionException("WARNING! List<Room> contains corrupted data.");
            }
        } catch (DataCorruptionException dce) {
            dce.printStackTrace();
        }
        if (room == null) {
            return false;
        }
        Hotel hotel = hotelDao.getById(room.getHotelId());
        if (hotel.getRoomsId().contains(room.getId())) {
            List<Room> hotelRooms = hotel.getRooms().stream()
                    .filter(aRoom -> !aRoom.getId().equals(room.getId()))
                    .collect(Collectors.toList());
            hotel.setRooms(hotelRooms);
        }
        if (!isContainId(room.getId())) {
            return false;
        }

        allRooms = roomList.stream()
                .filter(aRoom -> !aRoom.getId().equals(room.getId()))
                .collect(Collectors.toList());
        updateBase(allRooms);*/
        return true;
    }

    /*@Override
    public boolean isDataCorrupted(List<Room> roomList) {
        Room room;
        if (roomList == null) {
            return true;
        }
        if (roomList.isEmpty()) {
            return true;
        }
        for (Room aRoomList : roomList) {
            room = aRoomList;
            if (room == null) {
                return true;
            }
            if (room.getId() == null
                    || room.getPrice() == 0
                    || room.getNumberOfVisitors() == 0
                    || room.getHotelId() == null
                    || room.getFromDate() == null
                    || room.getToDate() == null) {
                return true;
            }
            if (!room.getFromDate().before(room.getToDate())) {
                return true;
            }
            if (room.getToDate().after(room.getFromDate()) && room.getUserId() == null) {
                return true;
            }
        }
        return false;
    }*/


}
