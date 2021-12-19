package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Author;

import java.util.Map;

public interface AuthorService extends BaseService<Author> {

    Map<Long, String> findAllByBookId(Long bookId);
}
