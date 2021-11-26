package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Author;

import java.util.Map;

public interface AuthorDao extends BaseDao<Author> {

    Map<Long, String> findAllByBookId(Long bookId);
}
