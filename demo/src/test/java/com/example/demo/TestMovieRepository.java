package com.example.demo;

import com.example.demo.domain.model.Movie;
import com.example.demo.repository.IMovieRepository;
import java.sql.Date;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
class TestMovieRepository {
    /*
    @Autowired
    IMovieRepository movieRepository;
    @Test
    void test() {
        Set<Movie> moviesRating = movieRepository.findAllByRatingGreaterThan(8.7);
        System.out.println("Movies rating greater than 8.7");
        for (Movie m : moviesRating) {
            System.out.println(m.getTitle() + ": " + m.getRating());
        }
        Set<Movie> moviesDate = movieRepository.findAllByReleaseDateBetween(Date.valueOf( "1997-03-10"), Date.valueOf( "2024-03-10"));
        System.out.println("Movies between: " + Date.valueOf( "1997-03-10") + " & " + Date.valueOf( "2024-03-10"));
        for (Movie m : moviesDate) {
            System.out.println(m.getTitle() + ": " + m.getReleaseDate());
        }

        Set<Movie> moviesDateBefore = movieRepository.findAllWithReleaseDateBefore(Date.valueOf( "1997-03-10"));
        System.out.println("Movies before: " + Date.valueOf( "1997-03-10"));
        for (Movie m : moviesDateBefore) {
            System.out.println(m.getTitle() + ": " + m.getReleaseDate());
        }

        Set<Movie> moviesDateAfter = movieRepository.findAllWithReleaseDateAfter(Date.valueOf( "2023-03-10"));
        System.out.println("Movies after: " + Date.valueOf( "2023-03-10"));
        for (Movie m : moviesDateAfter) {
            System.out.println(m.getTitle() + ": " + m.getReleaseDate());
        }

        Movie bestMovie = movieRepository.findFirstByOrderByRatingDesc();
        System.out.println("Best movie: " + bestMovie.getTitle() + "\nRating: " + bestMovie.getRating());
    }
     */
}
