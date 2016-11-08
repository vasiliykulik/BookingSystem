package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.entity.Room;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AbstractDaoImp<T extends Identity> implements AbstractDao<T> {

    public static Date currentDate = Calendar.getInstance().getTime();
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
    public T save(T t) {
        if (t == null) {
            System.out.println("This element cannot be saved");
            //TODO Exception
            return null;
        }
        if (update(t)) {
            return t;
        }
        List<T> all = getAll();
        all.add(t);
        updateBase(all);
        return t;
    };

    @Override
    public boolean delete(T t) {
        if (t == null || !isContainId(t.getId())) {
            return false;
        }
        List<T> all = getAll();
        Iterator<T> iterator = all.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element.getId().equals(t.getId())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public void updateBase(List<T> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(T t) {
        Long id = t.getId();

        if (!isContainId(id)) {
            return false;
        }
        List<T> all = getAll();
        Iterator<T> iterator = all.iterator();
        T element = iterator.next();
        if (element.getId() == id) {
            int index = all.indexOf(element);
            all.set(index, t);
        }
        return true;
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

    @Override
    public boolean isContainId(Long id) {

        return getAll().stream()
                .map(Identity::getId)
                .anyMatch(identity -> identity.equals(id));
    }

    @Override
    public T getLastSaved() {
        List<T> all = getAll();
        return all.get(all.size() - 1);
    }
}
