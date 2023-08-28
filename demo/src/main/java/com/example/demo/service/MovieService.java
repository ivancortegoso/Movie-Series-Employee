package com.example.demo.service;

import com.example.demo.domain.dto.response.MovieResponse;
import com.example.demo.domain.model.Movie;
import com.example.demo.repository.IMovieRepository;
import com.example.demo.repository.specification.MovieSpecification;
import com.example.demo.repository.specification.SearchCriteria;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends CommonService<Movie, IMovieRepository>{
    public Page<MovieResponse> findMoviesByCriteria(Pageable pageable, Double rating, LocalDate before, LocalDate after) {
        Page<Movie> movies = repository.findAll(new MovieSpecification(new SearchCriteria(rating, before, after)), pageable);
        return movies.map(MovieResponse::new);
    }

    public Page<MovieResponse> findMoviesByGenre(Pageable pageable, Long genreId) {
        Page<Movie> movies = repository.findAllByGenresId(pageable, genreId);
        return movies.map(MovieResponse::new);
    }

    public MovieResponse bestMovie() {
        return new MovieResponse(repository.findFirstByOrderByRatingDesc());
    }
}
