package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Publisher;

import java.util.Map;

public interface PublisherService extends BaseService<Publisher> {

    Map<Long, String> findAllByBookId(Long bookId);
}
