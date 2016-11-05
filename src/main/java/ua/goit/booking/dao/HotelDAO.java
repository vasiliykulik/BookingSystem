package ua.goit.booking.dao;

import ua.goit.booking.entity.Hotel;

import java.io.File;

/**
 * Created by taras on 04.11.16.
 * Refactored by Dima on 05.11.16.
 */
public class HotelDAO extends DAOImp<Hotel> {

    public HotelDAO() {
        super(new File("static/hotels.json"));
    }

}