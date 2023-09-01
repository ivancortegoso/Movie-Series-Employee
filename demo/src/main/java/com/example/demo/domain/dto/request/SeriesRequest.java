package com.example.demo.domain.dto.request;

import com.example.demo.domain.model.Genre;
import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesRequest {
    private String title;
    private String director;
    private LocalDate releaseDate;
    private Set<Genre> genres;
    private int length;
}
