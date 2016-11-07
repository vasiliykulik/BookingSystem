package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDaoImp<T extends Identity> implements AbstractDao<T> {

    private File file;
    private TypeReference<List<T>> typeReference;

    public AbstractDaoImp(File file, TypeReference<List<T>> typeReference) {
        this.file = file;
        this.typeReference = typeReference;
    }

    @Override
    public List<T> getAll() {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = new ArrayList<>();
        try {
            list = mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public T getById(long id) {
        List<T> result = getAll().stream()
                .filter(t -> t.getId() == id)
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<T> getAllById(List<Long> ids) {
        List<T> result = getAll().stream()
                .filter(t -> ids.stream().anyMatch(id -> id.equals(t.getId())))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public abstract T save(T t);

    @Override
    public abstract void delete(T t);

    @Override
    public void update(List<T> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isDataCorrupted(List<T> list) {
        if (list == null) {
            return true;
        }
        if (list.isEmpty()) {
            return true;
        }
        for (T aList : list) {
            if (aList == null) {
                return true;
            }
        }
        return false;
    }
}
