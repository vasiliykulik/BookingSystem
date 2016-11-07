package ua.goit.booking.controller;

import ua.goit.booking.dao.AbstractDao;
import ua.goit.booking.dao.HotelDao;
import ua.goit.booking.dao.HotelDaoImpl;
import ua.goit.booking.entity.Hotel;
import ua.goit.booking.entity.Room;
import ua.goit.booking.exception.OperationFailException;
import ua.goit.booking.exception.OperationSuccessException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class HotelController {

    public List<Hotel> findHotelByName(String name) {
        List<Hotel> result = new ArrayList<>();
        AbstractDao<Hotel> dao = new HotelDaoImpl();

  //      try {
            result.addAll(dao.getAll().stream()
                    .filter(hotel -> hotel.getHotelName().equals(name)).collect(Collectors.toList()));
            if (result.isEmpty()) {

                    return null;

                /*По несуществующему запросу "Radiss" метод List<Hotel> findHotelByName(String name) выдает
                 - NullPointerException - и прямо в методе не catchится,
                * так как мы заведомо осознанно передаем null:
                 * 1. можно ловить при вызове в Main
                  * 2. можно возвращать "по данному запросу ничего не найдено" вместо null
                  * 3. в методе прописать  - проверку name (есть ли вообще такой отель - или это ошибка ввода) -
                  * if(dao.getAll().stream()
                .anyMatch(hotel -> hotel.getHotelName().equals(name))){если да - проводим поиск и возвращаем Отели,
        } в другом случае {вернуть сообщение " Введите правильно название отеля/Такого отеля нет в нашей БД"
         (но как это реализовать ? - возвращаемый тип методом
         (сигнатура метода)это List<Hotel>). */
           }

   //     } catch (NullPointerException e) {
   //         System.out.println("Введите существующее навзвание");
  //      }
        return result;
    }

    public List<Hotel> findHotelByCity(String city) {
        List<Hotel> result = new ArrayList<>();
        HotelDao dao = new HotelDaoImpl();
        result.addAll(dao.getAll().stream()
                .filter(hotel -> hotel.getCityName().equals(city)).collect(Collectors.toList()));
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public void bookRoom(long roomId, long userId, long hotelId, Date fromDate, Date toDate) {
        HotelDao dao = new HotelDaoImpl();
        List<Hotel> hotels = dao.getAll();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (!room.isBooked()) {
                            room.setFromDate(fromDate);
                            room.setToDate(toDate);
                            room.setUserId(userId);
                            try {
                                throw new OperationSuccessException("Success! " + room + " has been booked!");
                            } catch (OperationSuccessException ose) {
                                ose.printStackTrace();
                            }
                            dao.update(hotels);
                            return;
                        } else {
                            try {
                                throw new OperationFailException("Sorry! " + room + " has already booked!");
                            } catch (OperationFailException ope) {
                                ope.printStackTrace();
                            }
                            return;
                        }
                    }
                }
                try {
                    throw new OperationFailException("Sorry! There's no such room in the hotel.");
                } catch (OperationFailException ope) {
                    ope.printStackTrace();
                }
                return;
            }
        }
        try {
            throw new OperationFailException("Sorry! There's no such hotels.");
        } catch (OperationFailException ofe) {
            ofe.printStackTrace();
        }
    }

    public void cancelReservation(long roomId, long userId, long hotelId) {
        HotelDao dao = new HotelDaoImpl();
        List<Hotel> hotels = dao.getAll();
        for (Hotel hotel : hotels) {
            if (hotelId == hotel.getId()) {
                for (Room room : hotel.getRooms()) {
                    if (roomId == room.getId()) {
                        if (room.isBooked()) {
                            if (room.getUserId() == userId) {
                                room.setToDate(room.getFromDate());
                                room.setUserId(null);
                                try {
                                    throw new OperationSuccessException("Success! "
                                            + room + " reservation has been canceled!");
                                } catch (OperationSuccessException ose) {
                                    ose.printStackTrace();
                                }
                                dao.update(hotels);
                                return;
                            } else {
                                try {
                                    throw new OperationFailException("Sorry! You haven't booked this room!");
                                } catch (OperationFailException ofe) {
                                    ofe.printStackTrace();
                                }
                                return;
                            }
                        } else {
                            try {
                                throw new OperationFailException("Sorry! " + room + " has not booked!");
                            } catch (OperationFailException ofe) {
                                ofe.printStackTrace();
                            }
                            return;
                        }
                    }
                }
                try {
                    throw new OperationFailException("Sorry! There's no such room in the hotel.");
                } catch (OperationFailException ofe) {
                    ofe.printStackTrace();
                }
                return;
            }
        }
        System.out.println("Sorry! No such hotel.");
    }

    public List<Hotel> findRoom(Map<String, String> params) {
        List<Hotel> result;
        Set<Hotel> hotels = new HashSet<>();
        HotelDao dao = new HotelDaoImpl();

        for (String key : params.keySet()) {
            for (Field field : Room.getFieldsName()) {
                if (field.getName().equals(key)) {
                    String value = params.get(key);
                    for (Hotel hotel : dao.getAll()) {
                        for (Room room : hotel.getRooms()) {
                            Field f = null;
                            try {
                                f = room.getClass().getDeclaredField(key);
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                            if (f != null) {
                                f.setAccessible(true);
                            }
                            try {
                                if (f != null && f.get(room).toString().equals(value)) {
                                    hotels.add(hotel);
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        result = new ArrayList<Hotel>(hotels);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public List<Room> getAllFreeRooms(Long hotelId) {
        HotelDao hotelDao = new HotelDaoImpl();
        Date currentDate = Calendar.getInstance().getTime();
        List<Room> result = hotelDao.getById(hotelId).getRooms().stream()
                .filter(room -> !(room.getToDate().after(currentDate) && room.getFromDate().before(currentDate)))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

}
