package ua.com.alevel.service;

import java.util.List;

public interface ElasticBookSearchService {

    List<String> searchBookName(String query);
}
