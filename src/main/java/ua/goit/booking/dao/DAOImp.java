package ua.goit.booking.dao;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.entity.Identity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by taras on 04.11.16.
 * Refactored by Dima on 05.11.16.
 */
public class DAOImp<T extends Identity> implements DAO<T> {

    private File file;
    private TypeReference<ArrayList<T>> typeReference;

    public DAOImp(File file, TypeReference typeReference) {
        this.file = file;
        this.typeReference = typeReference;
    }

    @Override
    public List<T> getAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = new ArrayList<>();
        try {
            list = mapper.readValue(file, typeReference);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public T getById(long id) {
        List<T> res = getAll().stream()
                .filter(t -> t.getId() == id)
                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<T> getAllById(List<Long> ids) {
        List<T> res = getAll().stream()
                .filter(t -> ids.stream().anyMatch(id -> id.equals(t.getId())))
                .collect(Collectors.toList());
        return res;
    }

    @Override
    public void update(List<T> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, list);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
