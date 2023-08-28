package com.example.demo.repository;

import com.example.demo.domain.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    Page<Movie> findAllByGenresId(Pageable pageable, Long genreId);

    Movie findFirstByOrderByRatingDesc();

}
