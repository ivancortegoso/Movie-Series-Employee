package com.example.demo.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.domain.dto.response.SearchMovieTmbd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "movie")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends EntityParent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @ManyToMany
    private Set<Genre> genres;

    @Column(nullable = false)
    private int length;

    @ManyToOne
    private Employee proposer;

    @ManyToOne
    private Employee implementer;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private LocalDate dateProposed;

    public Movie(String title, String director, LocalDate releaseDate, Set<Genre> genres, int length, Employee proposer, Employee implementer) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.length = length;
        this.proposer = proposer;
        this.implementer = implementer;
    }

    public Movie(SearchMovieTmbd movieTmbd, Employee proposer, Employee implementer,Set<Genre> genres, String director) {
        this.id = movieTmbd.getId().longValue();
        this.title = movieTmbd.getOriginal_title();
        this.releaseDate = movieTmbd.getRelease_date();
        this.length = movieTmbd.getRuntime();
        this.proposer = proposer;
        this.implementer = implementer;
        this.genres = genres;
        this.director = director;
        this.dateProposed = LocalDate.now();
    }

}
