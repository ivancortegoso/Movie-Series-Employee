package com.example.demo.controller;

import com.example.demo.domain.dto.request.EmployeeRequest;
import com.example.demo.domain.dto.request.MovieRequest;
import com.example.demo.domain.dto.request.SeriesRequest;
import com.example.demo.domain.model.Employee;
import com.example.demo.domain.model.Movie;
import com.example.demo.domain.model.Series;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.MovieService;
import com.example.demo.service.SeriesService;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/implementer/")
public class ImplementerController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    MovieService movieService;
    @Autowired
    SeriesService seriesService;

    @DeleteMapping("movie/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) throw new EntityNotFoundException("Movie not found");
        movieService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("series/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        Series series = seriesService.findById(id);
        if (series == null) throw  new EntityNotFoundException("Series not found");
        seriesService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("employee/{email}")
    @ResponseBody
    public ResponseEntity<Void> deleteEmployee(@PathVariable String email){
        Employee employee = employeeService.findByEmail(email);
        if (employee == null) throw new EntityNotFoundException("Employee not found");
        employeeService.deleteByEmail(employee);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "employee/{email}")
    @ResponseBody
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeRequest request, @PathVariable String email) {
        Employee employee = employeeService.findByEmail(email);
        if (employee == null) throw new EntityNotFoundException("User not found");
        employeeService.updateEmployee(employee, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "movie/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateMovie(@RequestBody MovieRequest request, @PathVariable Long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) throw new EntityNotFoundException("Movie not found");
        movieService.updateMovie(movie, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "series/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateSeries(@RequestBody SeriesRequest request, @PathVariable Long id) {
        Series series = seriesService.findById(id);
        if (series == null) throw new EntityNotFoundException("Series not found");
        seriesService.updateSeries(series, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "movie")
    @ResponseBody
    public ResponseEntity<Void> postMovie(@RequestParam String title, @RequestParam String proposerEmail) {
        movieService.addMovie(title, proposerEmail);
        return ResponseEntity.ok().build();
    }

}
