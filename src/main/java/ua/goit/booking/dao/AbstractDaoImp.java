package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.dao.exception.AbstractDaoException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractDaoImp<T extends Identity> implements AbstractDao<T> {

    private File file;
    private TypeReference<List<T>> objectsFromDB;

    public AbstractDaoImp(File file, TypeReference<List<T>> objectsFromDB) {
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        this.file = file;
        this.objectsFromDB = objectsFromDB;
    }

    @Override
    public List<T> getAll() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, objectsFromDB);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public T getById(long id) throws AbstractDaoException {
        List<T> result = getAll().stream()
                .filter(t -> t.getId() == id)
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new AbstractDaoException("Error! No data with such ID");
        }
        return result.get(0);
    }

    @Override
    public List<T> getAllById(List<Long> ids) {
        return getAll().stream()
                .filter(t -> ids.stream().anyMatch(id -> id != null && id.equals(t.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public T save(T t) throws AbstractDaoException {
        if (t == null) {
            throw new AbstractDaoException("This element is \"null\" and cannot be saved");
        }
        List<T> list = getAll();
        int index = getIndexOf(list, t);
        if (index < 0) {
            list.add(t);
        } else {
            list.set(index, t);
        }
        saveToJson(list);
        return t;
    }

    private void saveToJson(List<T> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getIndexOf(List<T> list, T t) {
        for (T element : list) {
            if (element.equals(t)) {
                return list.indexOf(element);
            }
        }
        return -1;
    }

    @Override
    public boolean delete(T t) throws AbstractDaoException {
        if (t == null) {
            throw new AbstractDaoException("This element is \"null\" and cannot be deleted");
        }
        List<T> list = getAll();
        if (!list.contains(t)) {
            throw new AbstractDaoException("This element has not been fount in DB");
        }
        list.removeIf(element -> element.getId().equals(t.getId()));
        saveToJson(list);
        return true;
    }

    @Override
    public boolean deleteById(long id) throws AbstractDaoException {
        return delete(getById(id));
    }

}
