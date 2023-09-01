package com.example.demo.service;

import com.example.demo.domain.dto.request.MovieRequest;
import com.example.demo.domain.dto.response.MovieResponse;
import com.example.demo.domain.mapper.MovieMapper;
import com.example.demo.domain.model.Employee;
import com.example.demo.domain.model.Movie;
import com.example.demo.repository.IMovieRepository;
import com.example.demo.repository.specification.MovieSpecification;
import com.example.demo.repository.specification.SearchCriteria;
import java.time.LocalDate;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends CommonService<Movie, IMovieRepository, Long>{
    @Autowired
    MovieMapper mapper;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    TmdbService tmdbService;

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

    public void updateMovie(Movie movie, MovieRequest request) {
        mapper.updateMovie(request, movie);
        repository.save(movie);
    }

    public void addMovie(String title, String email) {
        Employee proposer = employeeService.findByEmail(email);
        if (proposer == null) throw new EntityNotFoundException("Proposer not found");
        Employee implementer = employeeService.getLogged();
        Movie newMovie = tmdbService.getMovie(title, proposer, implementer);
        repository.save(newMovie);
    }

    public Movie findByTitle(String title) {
        return repository.findByTitle(title);
    }

}
