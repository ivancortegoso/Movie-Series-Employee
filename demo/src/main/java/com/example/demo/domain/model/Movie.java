package com.example.demo.domain.model;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "movie")
@Getter
@Setter
@Entity
public class Movie {
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

}
