package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.BookDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public DataTableResponse<Book> findAll(DataTableRequest request) {
        DataTableResponse<Book> dataTableResponse = bookDao.findAll(request);
        long count = bookDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findByAuthorId(Long id) {
        return bookDao.findByAuthorId(id);
    }
}
