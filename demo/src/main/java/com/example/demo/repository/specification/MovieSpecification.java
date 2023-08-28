package com.example.demo.repository.specification;

import com.example.demo.domain.model.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

@AllArgsConstructor
public class MovieSpecification implements Specification<Movie> {
    private transient SearchCriteria criteria;

    @Override
    public Predicate toPredicate
            (@NonNull Root<Movie> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getRating() != null) {
            predicates.add(builder.greaterThan(root.get("rating"), criteria.getRating()));
        }
        if (criteria.getAfter() != null) {
            predicates.add(builder.greaterThan(root.get("releaseDate"), criteria.getAfter()));
        }
        if (criteria.getBefore() != null) {
            predicates.add(builder.lessThan(root.get("releaseDate"), criteria.getBefore()));
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
