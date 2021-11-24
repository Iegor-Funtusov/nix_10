package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.BookDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookDaoImpl implements BookDao {

    private final JpaConfig jpaConfig;

    private static final String FIND_ALL_BOOKS_QUERY = "select * from books";

    public BookDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
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
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Book> findAll(DataTableRequest request) {
        List<Book> books = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_BOOKS_QUERY)) {
            while (resultSet.next()) {
                books.add(initBookByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Book> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(books);
        return dataTableResponse;
    }

    @Override
    public long count() {
        return 0;
    }

    private Book initBookByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String bookName = resultSet.getString("book_name");
        String description = resultSet.getString("description");
        String imageUrl = resultSet.getString("image_url");
        int pageSize = resultSet.getInt("page_size");
        int publicationDate = resultSet.getInt("publication_date");

        Book book = new Book();
        book.setId(id);
        book.setCreated(created);
        book.setUpdated(updated);
        book.setVisible(visible);
        book.setBookName(bookName);
        book.setDescription(description);
        book.setImageUrl(imageUrl);
        book.setPageSize(pageSize);
        book.setPublicationDate(publicationDate);

        return book;
    }
}
