package com.example.demo.service;

import com.example.demo.domain.model.BiKeyID;
import com.example.demo.domain.model.Movie;
import com.example.demo.domain.model.MovieRatingEmployee;
import com.example.demo.repository.IMovieRatingEmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingEmployeeService extends CommonService<MovieRatingEmployee, IMovieRatingEmployeeRepository, BiKeyID> {
    public Long countById1(Movie movie) {
        return repository.countAllById1(movie);
    }
}
