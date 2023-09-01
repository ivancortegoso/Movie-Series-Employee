package com.example.demo.domain.model;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SeriesRatingEmployee that = (SeriesRatingEmployee) o;
        return Objects.equals(id1, that.id1) && Objects.equals(id2, that.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }
}
