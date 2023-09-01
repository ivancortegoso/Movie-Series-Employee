package com.example.demo.controller;

import com.example.demo.domain.dto.exception.ApiErrorException;
import com.example.demo.domain.dto.response.MovieResponse;
import com.example.demo.service.GenreService;
import com.example.demo.service.MovieService;
import java.time.LocalDate;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private GenreService genreService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<MovieResponse>> getAllMovies(@PageableDefault(size = 50) final Pageable pageable, @RequestParam(required = false) Double rating, @RequestParam(required = false) LocalDate before, @RequestParam(required = false) LocalDate after)
            throws ApiErrorException {
        if (rating != null && (rating > 10 || rating < 0)) throw new ApiErrorException("Rating must be between 0 and 10");
        if (before != null && after != null && before.isAfter(after)) throw new ApiErrorException("Date before must be before Date after");
        Page<MovieResponse> movies = movieService.findMoviesByCriteria(pageable, rating, before, after);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(path = "/genre")
    @ResponseBody
    public ResponseEntity<Page<MovieResponse>> getAllMoviesByGenre(@PageableDefault(page = 0, size = 50) final Pageable pageable, @RequestParam Long genreId) {
        if (genreService.findById(genreId) == null) throw new EntityNotFoundException("Genre id not found");
        Page<MovieResponse> movies = movieService.findMoviesByGenre(pageable, genreId);
        return ResponseEntity.ok(movies);
    }

    @GetMapping(path = "/best")
    @ResponseBody
    public ResponseEntity<MovieResponse> getBestMovie() {
        MovieResponse movie = movieService.bestMovie();
        return ResponseEntity.ok(movie);
    }

}
