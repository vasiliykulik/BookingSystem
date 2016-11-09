package ua.goit.booking.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.booking.dao.exception.AbstractDaoException;
import ua.goit.booking.exception.DataCorruptionException;
import ua.goit.booking.exception.OperationFailException;

import java.io.File;
import java.io.IOException;
import java.util.*;
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
        try {
            return mapper.readValue(file, typeReference);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public T getById(long id) throws AbstractDaoException {
        List<T> result = null;
        List<T> tList;
        T t1 = null;
        try {
            tList = getAll();
//            try {
//                if (isDataCorrupted(tList)) {
//                    throw new DataCorruptionException("WARNING! DB contains corrupted data.");
//                }
//            } catch (DataCorruptionException dce) {
//                dce.printStackTrace();
//            }
            result = tList.stream()
                    .filter(t -> t.getId() == id)
                    .collect(Collectors.toList());
            if (result.isEmpty()) {
                throw new AbstractDaoException("Error! No data with such ID");
            }
            t1 = result.get(0);
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return t1;
    }

    @Override
    public List<T> getAllById(List<Long> ids) {
        List<T> result = null;
        List<T> allList = getAll();
//        try {
//            if (ids.isEmpty()) {
//                throw new OperationFailException("WARNING! Transferred data is not correct");
//            }
//        } catch (RuntimeException rx) {
//            rx.printStackTrace();
//        }
//
//        if (!isDataCorrupted(allList)) {
        result = allList.stream()
                .filter(t -> ids.stream().anyMatch(id -> id.equals(t.getId())))
                .collect(Collectors.toList());
//        }
//        try {
//            if (result.isEmpty()) {
//                throw new DataCorruptionException("Error! No data with such IDs");
//            } else if (result == null) {
//                throw new DataCorruptionException("WARNING! Data not available");
//            }
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        }
        return result;
    }

    @Override
    public T save(T t) throws AbstractDaoException {
        if (t == null) {
            throw new AbstractDaoException("This element cannot be saved");
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

    private int getIndexOf(List<T> list, T t) {
        for (T element : list) {
            if (element.equals(t)) {
                return list.indexOf(element);
            }
        }
        return -1;
    }

    @Override
    public boolean delete(T t) {
      /*  if (t == null || !isContainId(t.getId())) {
            return false;
        }
        List<T> all = getAll();
        try {
            if (isDataCorrupted(all)) {
                throw new DataCorruptionException("WARNING! Data not available");
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        Iterator<T> iterator = all.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element.getId().equals(t.getId())) {
                iterator.remove();
            }
        }*/
        return true;
    }

    //    @Override
    private void saveToJson(List<T> list) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*

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
*/

   /* @Override
    public boolean isContainId(Long id) {
        if (id == null)
            return false;

        List<T> all = getAll();
        try {
            if (isDataCorrupted(all)) {
                throw new DataCorruptionException("WARNING! Data not available");
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return all.stream()
                .map(Identity::getId)
                .anyMatch(identity -> identity.equals(id));
    }*/
}
