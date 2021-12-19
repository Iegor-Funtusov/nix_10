package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.util.List;

public interface BaseDao<E extends BaseEntity> {

    void create(E e);
    void update(E e);
    void delete(Long id);
    void delete(E e);
    boolean existById(Long id);
    E findById(Long id);
    List<E> findAll(int page, int size, String sort, String order);
}
