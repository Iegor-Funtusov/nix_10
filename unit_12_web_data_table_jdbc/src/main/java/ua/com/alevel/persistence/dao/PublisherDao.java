package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Publisher;

import java.util.Map;

public interface PublisherDao extends BaseDao<Publisher> {

    Map<Long, String> findAllByBookId(Long bookId);
}
