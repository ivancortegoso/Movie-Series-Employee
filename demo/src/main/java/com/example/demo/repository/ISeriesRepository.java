package com.example.demo.repository;

import com.example.demo.domain.model.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeriesRepository extends JpaRepository<Series, Long>, JpaSpecificationExecutor<Series> {

    Series findFirstByOrderByRatingDesc();

    Series findByTitle(String title);

    Page<Series> findAllByGenresId(Pageable pageable, Long id);

    Page<Series> findAllByStatus(Pageable pageable, String status);

    Page<Series> findAllByProposerId(Pageable pageable, Long proposerId);
}
