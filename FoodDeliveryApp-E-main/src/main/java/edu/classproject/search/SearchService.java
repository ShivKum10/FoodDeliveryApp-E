package edu.classproject.search;

import java.util.List;

public interface SearchService {
    List<SearchResult> searchRestaurants(String query);
}
