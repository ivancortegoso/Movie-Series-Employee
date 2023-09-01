package com.example.demo.domain.model;

import java.util.Objects;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie_rating_employee")
@IdClass(BiKeyID.class)
@AllArgsConstructor
@NoArgsConstructor
public class MovieRatingEmployee {
    @Id
    @ManyToOne
    private Movie id1;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
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
        MovieRatingEmployee that = (MovieRatingEmployee) o;
        return Objects.equals(id1, that.id1)
                && Objects.equals(id2, that.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }
}
