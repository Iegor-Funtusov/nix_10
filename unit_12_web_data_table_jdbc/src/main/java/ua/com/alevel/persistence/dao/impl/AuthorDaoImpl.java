package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.AuthorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class AuthorDaoImpl implements AuthorDao {

    private final JpaConfig jpaConfig;

    public AuthorDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private static final String CREATE_AUTHOR_QUERY = "insert into authors values(default, ?,?,?,?,?)";
    private static final String FIND_AUTHOR_BY_ID_QUERY = "select * from authors where id = ";
    private static final String FIND_AUTHOR_BY_BOOK_ID_QUERY = "select id, first_name, last_name from authors left join author_book ab on authors.id = ab.author_id where book_id = ";

    @Override
    public void create(Author entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_AUTHOR_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Author entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Author findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_AUTHOR_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return convertResultSetToAuthor(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Author> findAll(DataTableRequest request) {
        List<Author> authors = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, first_name, last_name, count(author_id) as bookCount " +
                "from authors as author left join author_book as ab on author.id = ab.author_id " +
                "group by author.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();


        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                AuthorResultSet authorResultSet = convertResultSetToSimpleAuthor(resultSet);
                authors.add(authorResultSet.getAuthor());
                otherParamMap.put(authorResultSet.getAuthor().getId(), authorResultSet.getBookCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Author> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(authors);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from authors")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private AuthorResultSet convertResultSetToSimpleAuthor(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int bookCount = resultSet.getInt("bookCount");

        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return new AuthorResultSet(author, bookCount);
    }

    private Author convertResultSetToAuthor(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        Author author = new Author();
        author.setId(id);
        author.setCreated(created);
        author.setUpdated(updated);
        author.setVisible(visible);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return author;
    }

    @Override
    public Map<Long, String> findAllByBookId(Long bookId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_AUTHOR_BY_BOOK_ID_QUERY + bookId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                map.put(id, firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static class AuthorResultSet {

        private final Author author;
        private final int bookCount;

        private AuthorResultSet(Author author, int bookCount) {
            this.author = author;
            this.bookCount = bookCount;
        }

        public Author getAuthor() {
            return author;
        }

        public int getBookCount() {
            return bookCount;
        }
    }
}
