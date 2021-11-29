package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookDao extends BaseDao<Book> {

    Map<Long, String> findByAuthorId(Long id);
    List<Book> findAllByAuthorId(Long id);
}
