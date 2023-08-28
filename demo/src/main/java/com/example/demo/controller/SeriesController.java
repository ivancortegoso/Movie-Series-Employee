package com.example.demo.controller;

import com.example.demo.domain.dto.exception.ApiErrorException;
import com.example.demo.domain.dto.response.SeriesResponse;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.GenreService;
import com.example.demo.service.SeriesService;
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
@RequestMapping(path = "/api/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<SeriesResponse>> getAllSeries(@PageableDefault(size = 50) final Pageable pageable, @RequestParam(required = false) Double rating, @RequestParam(required = false) LocalDate before, @RequestParam(required = false) LocalDate after)
            throws ApiErrorException {
        if (rating != null && (rating > 10 || rating < 0)) throw new ApiErrorException("Rating must be between 0 and 10");
        if (before != null && after != null && before.isAfter(after)) throw new ApiErrorException("Date before must be before Date after");
        Page<SeriesResponse> series = seriesService.findSeriesByCriteria(pageable, rating, before, after);
        return ResponseEntity.ok(series);
    }

    @GetMapping(path = "/genre")
    @ResponseBody
    public ResponseEntity<Page<SeriesResponse>> getAllSeriesByGenre(@PageableDefault(size = 50) final Pageable pageable, Long genreId) {
        if (genreService.findById(genreId) == null) throw new EntityNotFoundException("Genre id not found");
        Page<SeriesResponse> series = seriesService.findAllByGenresId(pageable, genreId);
        return ResponseEntity.ok(series);
    }

    @GetMapping(path = "/best")
    @ResponseBody
    public ResponseEntity<SeriesResponse> getBestSeries() {
        SeriesResponse series = seriesService.bestSeries();
        return ResponseEntity.ok(series);
    }

    @GetMapping(path = "/status")
    @ResponseBody
    public ResponseEntity<Page<SeriesResponse>> getAllSeriesByStatus(@PageableDefault(size = 50) final Pageable pageable, String status) {
        Page<SeriesResponse> series = seriesService.findAllByStatus(pageable, status);
        return ResponseEntity.ok(series);
    }

    @GetMapping(path = "/proposer")
    @ResponseBody
    public ResponseEntity<Page<SeriesResponse>> getAllSeriesByProposerId(@PageableDefault(size = 50) final Pageable pageable, Long id) {
        if (employeeService.findById(id) == null) throw new EntityNotFoundException("Employee with id: " + id + " does not exist.");
        Page<SeriesResponse> series = seriesService.findAllByProposerId(pageable, id);
        return ResponseEntity.ok(series);
    }

    @GetMapping(path = "/title")
    @ResponseBody
    public ResponseEntity<SeriesResponse> getSeriesByTitle(String title) {
        return ResponseEntity.ok(seriesService.findByTitle(title));
    }

}
