package com.example.demo.service;

import com.example.demo.domain.dto.response.PageTmdb;
import javax.persistence.EntityNotFoundException;

import com.example.demo.domain.dto.response.SearchMovieTmbd;
import com.example.demo.domain.model.Employee;
import com.example.demo.domain.model.Genre;
import com.example.demo.domain.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

@Service
public class TmdbService {
    @Autowired
    GenreService genreService;
    private RestTemplate rest = new RestTemplate();
    private final static String SEARCH_MOVIE_URL = "https://api.themoviedb.org/3/search/movie";
    private final static String SEARCH_MOVIE_URL_BY_ID = "https://api.themoviedb.org/3/movie/{id}";

    public Integer getIdFromMovie(String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NWE1OTE2YTBlMmM0YjhhMjBmMGY0NGE1MmZiZjYxYyIsInN1YiI6IjY0ZTg5NDA3OTBlYTRiMDBhZWZhYTY3YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RCZilHcmWeKKBRjXTKUwpM3CVDVigS_W1ryDue80njo");
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(SEARCH_MOVIE_URL)
                .queryParam("query", title)
                .queryParam("include_adult", "false")
                .queryParam("language", "en-US")
                .queryParam("page", "1")
                .build()
                .toUriString();
        ResponseEntity<PageTmdb> responseEntity = rest.exchange(urlTemplate, HttpMethod.GET, requestEntity,
                PageTmdb.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            PageTmdb searchPage = responseEntity.getBody();
            return (Integer)responseEntity.getBody().getResults().get(0).get("id");
        } else {
            throw new EntityNotFoundException("Not movies with title: " + title + " were found in TMDB");
        }
    }
    public Movie getMovie(String title, Employee proposer, Employee implementer) {
        Integer id = this.getIdFromMovie(title);
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "application/json");
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NWE1OTE2YTBlMmM0YjhhMjBmMGY0NGE1MmZiZjYxYyIsInN1YiI6IjY0ZTg5NDA3OTBlYTRiMDBhZWZhYTY3YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RCZilHcmWeKKBRjXTKUwpM3CVDVigS_W1ryDue80njo");
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id+"");
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(SEARCH_MOVIE_URL_BY_ID)
                .buildAndExpand(params)
                .encode()
                .toUriString();
        ResponseEntity<SearchMovieTmbd> responseEntity = rest.exchange(urlTemplate, HttpMethod.GET, requestEntity,
                SearchMovieTmbd.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            SearchMovieTmbd movieTmbd = responseEntity.getBody();
            Set<Genre> genres = new HashSet<>();
            for (LinkedHashMap map : movieTmbd.getGenres()) {
                Integer genreId = (Integer) map.get("id");
                genres.add(genreService.findById(genreId.longValue()));
            }
            Movie newMovie = new Movie(movieTmbd, proposer, implementer, genres, "to be done");
            return newMovie;
        } else {
            throw new EntityNotFoundException("Not movies with id: " + id + " were found in TMDB");
        }
    }
}
