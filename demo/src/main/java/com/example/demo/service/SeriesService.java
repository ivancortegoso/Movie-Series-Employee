package com.example.demo.service;

import com.example.demo.domain.dto.response.SeriesResponse;
import com.example.demo.domain.model.Series;
import com.example.demo.repository.ISeriesRepository;
import com.example.demo.repository.specification.SearchCriteria;
import com.example.demo.repository.specification.SeriesSpecification;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SeriesService extends CommonService<Series, ISeriesRepository>{

    public Page<SeriesResponse> findSeriesByCriteria(Pageable pageable, Double rating, LocalDate before, LocalDate after) {
        Page<Series> series = repository.findAll(new SeriesSpecification(new SearchCriteria(rating, before, after)), pageable);
        return series.map(SeriesResponse::new);
    }

    public SeriesResponse bestSeries() {
        return new SeriesResponse(repository.findFirstByOrderByRatingDesc());
    }

    public SeriesResponse findByTitle(String title) {
        return new SeriesResponse(repository.findByTitle(title));
    }

    public Page<SeriesResponse> findAllByProposerId(Pageable pageable, Long id) {
        Page<Series> series = repository.findAllByProposerId(pageable, id);
        return series.map(SeriesResponse::new);
    }

    public Page<SeriesResponse> findAllByGenresId(Pageable pageable, Long id) {
        Page<Series> series = repository.findAllByGenresId(pageable, id);
        return series.map(SeriesResponse::new);
    }

    public Page<SeriesResponse> findAllByStatus(Pageable pageable, String status) {
        Page<Series> series = repository.findAllByStatus(pageable, status);
        return series.map(SeriesResponse::new);
    }
}
