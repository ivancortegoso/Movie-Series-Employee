package com.example.demo.domain.dto.response;

import com.example.demo.domain.model.Genre;
import com.example.demo.domain.model.Movie;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    public MovieResponse(Movie movie) {
        if (movie == null) throw new EntityNotFoundException("Movies not found");
        this.title = movie.getTitle();
        this.releaseDate = movie.getReleaseDate();
        for (Genre g : movie.getGenres()) this.genres.add(g.getValue());
        this.length = movie.getLength();
        this.proposer = movie.getProposer().getFirstName() + " " + movie.getProposer().getLastName();
        this.implementer = movie.getImplementer().getFirstName() + " " + movie.getImplementer().getLastName();
        this.rating = movie.getRating();
        this.dateProposed = movie.getDateProposed();
    }

    private String title;
    private LocalDate releaseDate;
    private Set<String> genres = new HashSet<>();
    private int length;
    private String proposer;
    private String implementer;
    private double rating;
    private LocalDate dateProposed;
}
