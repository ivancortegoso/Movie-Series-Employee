package com.example.demo.repository.specification;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchCriteria {
    private Double rating;
    private LocalDate before;
    private LocalDate after;
}
