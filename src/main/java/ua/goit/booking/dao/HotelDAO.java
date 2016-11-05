package ua.goit.booking.dao;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.entity.Hotel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taras on 04.11.16.
 * Refactored by Dima on 05.11.16.
 */
public class HotelDAO extends DAOImp<Hotel> {

    public HotelDAO() {
        super(new File("static/info.json"), new TypeReference<ArrayList<Hotel>>() {
        });
    }
//
//    @Override
//    public List<Hotel> getAll() {
//        ObjectMapper mapper = new ObjectMapper();
//        List<Hotel> list = new ArrayList<>();
//        try {
//            list = mapper.readValue(new File("static/info.json"), new TypeReference<ArrayList<Hotel>>() {
//            });
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

}