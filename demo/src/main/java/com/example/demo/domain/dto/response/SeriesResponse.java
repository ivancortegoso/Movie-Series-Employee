package com.example.demo.domain.dto.response;

import com.example.demo.domain.model.Genre;
import com.example.demo.domain.model.Series;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeriesResponse {

    public SeriesResponse(Series series) {
        if (series == null) throw new EntityNotFoundException("Series not found");
        this.title = series.getTitle();
        this.releaseDate = series.getReleaseDate();
        for (Genre g : series.getGenres()) this.genres.add(g.getValue());
        this.seasons = series.getSeasons();
        this.status = series.getStatus();
        this.proposer = series.getProposer().getFirstName() + " " + series.getProposer().getLastName();
        this.implementer = series.getImplementer().getFirstName() + " " + series.getImplementer().getLastName();
        this.rating = series.getRating();
        this.dateProposer = series.getDateProposed();
    }

    private String title;
    private LocalDate releaseDate;
    private Set<String> genres = new HashSet<>();
    private int seasons;
    private String status;
    private String proposer;
    private String implementer;
    private double rating;
    private LocalDate dateProposer;
}
