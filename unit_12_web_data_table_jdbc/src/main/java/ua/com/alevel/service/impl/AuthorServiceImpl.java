package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.AuthorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void create(Author entity) {
        authorDao.create(entity);
    }

    @Override
    public void update(Author entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Author findById(Long id) {
        return authorDao.findById(id);
    }

    @Override
    public DataTableResponse<Author> findAll(DataTableRequest request) {
        DataTableResponse<Author> dataTableResponse = authorDao.findAll(request);
        long count = authorDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllByBookId(Long bookId) {
        return authorDao.findAllByBookId(bookId);
    }
}
