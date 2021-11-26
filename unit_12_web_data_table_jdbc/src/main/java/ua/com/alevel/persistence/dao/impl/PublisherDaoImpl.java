package ua.com.alevel.persistence.dao.impl;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.PublisherDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.persistence.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PublisherDaoImpl implements PublisherDao {

    private final JpaConfig jpaConfig;

    private static final String FIND_ALL_BY_BOOK_ID = "select id, name from publishers left join publisher_book pb on publishers.id = pb.publisher_id where pb.book_id = ";

    public PublisherDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
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
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Publisher findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Publisher> findAll(DataTableRequest request) {
        List<Publisher> list = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select p.id, p.name, p.country, count(publisher_id) as book_count from publishers as p left join publisher_book as pb on p.id = pb.publisher_id group by p.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                PublisherResultSet publisherResultSet = convertResultSetToSimplePublisher(resultSet);
                list.add(publisherResultSet.getPublisher());
                otherParamMap.put(publisherResultSet.getPublisher().getId(), publisherResultSet.getBookCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Publisher> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(list);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    private PublisherResultSet convertResultSetToSimplePublisher(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        int bookCount = resultSet.getInt("book_count");

        Publisher publisher = new Publisher();
        publisher.setId(id);
        publisher.setName(name);
        publisher.setCountry(CountryCode.getByCode(country));

        return new PublisherResultSet(publisher, bookCount);
    }

    private static class PublisherResultSet {

        private final Publisher publisher;
        private final int bookCount;

        private PublisherResultSet(Publisher publisher, int bookCount) {
            this.publisher = publisher;
            this.bookCount = bookCount;
        }

        public Publisher getPublisher() {
            return publisher;
        }

        public int getBookCount() {
            return bookCount;
        }
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from publishers")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<Long, String> findAllByBookId(Long bookId) {
        Map<Long, String> map = new HashMap<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_BY_BOOK_ID + bookId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                map.put(id, name);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }
}
