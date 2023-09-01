package com.example.demo.controller;

import com.example.demo.domain.dto.exception.ApiErrorException;
import com.example.demo.domain.dto.response.EmployeeResponse;
import com.example.demo.domain.model.Movie;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

@RestController
@RequestMapping(path = "/api/employee/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MovieService movieService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(@PageableDefault(size = 50) final Pageable pageable) {
        Page<EmployeeResponse> employeeResponses = employeeService.findAllEmployees(pageable);
        return ResponseEntity.ok(employeeResponses);
    }

    @PostMapping(path = "/api/employee/movie")
    @ResponseBody
    public ResponseEntity<Void> voteMovie(@RequestParam String title, @RequestParam double rating) throws ApiErrorException {
        Movie movie = movieService.findByTitle(title);
        if (movie == null) throw new EntityNotFoundException("Movie not found");
        if (movie.getDateProposed().isBefore(LocalDate.now().minus(7, ChronoUnit.DAYS)))
            throw new ApiErrorException("Vote closed");
        Movie updatedMovie = employeeService.voteMovie(movie, rating);
        movieService.save(updatedMovie);
        return ResponseEntity.ok().build();
    }

}
