package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Book;

import java.util.Map;

public interface BookService extends BaseService<Book> {

    Map<Long, String> findByAuthorId(Long id);
}
