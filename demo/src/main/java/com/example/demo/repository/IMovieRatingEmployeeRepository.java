package com.example.demo.repository;

import com.example.demo.domain.model.BiKeyID;
import com.example.demo.domain.model.Movie;
import com.example.demo.domain.model.MovieRatingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRatingEmployeeRepository extends JpaRepository<MovieRatingEmployee, BiKeyID>{

    Long countAllById1(Movie movie);
}
