package com.example.demo.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchMovieTmbd {
    private List<LinkedHashMap> genres;
    private Integer id;
    private String original_title;
    private LocalDate release_date;
    private int runtime;

}
