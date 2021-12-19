package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.PublisherDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Publisher;
import ua.com.alevel.service.PublisherService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherDao publisherDao;

    public PublisherServiceImpl(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @Override
    public void create(Publisher entity) {

    }

    @Override
    public void update(Publisher entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Publisher findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Publisher> findAll(DataTableRequest request) {
        DataTableResponse<Publisher> dataTableResponse = publisherDao.findAll(request);
        long count = publisherDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllByBookId(Long bookId) {
        return publisherDao.findAllByBookId(bookId);
    }
}
