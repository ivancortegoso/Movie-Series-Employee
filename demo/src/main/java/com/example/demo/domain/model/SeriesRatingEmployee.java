package com.example.demo.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "series_rating_employee")
@IdClass(BiKeyID.class)
public class SeriesRatingEmployee {
    @Id
    @ManyToOne
    private Series id1;

    @Id
    @ManyToOne
    private Employee id2;

    @Column(nullable = false)
    private double rating;
}
