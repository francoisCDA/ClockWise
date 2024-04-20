package fr.labom2i.spi.port;

import java.util.List;

public interface BaseCrudRepo<T>  {

    T findById(Long id);

    List<T> findAll();

    boolean save(T t);

    boolean delete(Long id);

    boolean update(T t);

}
