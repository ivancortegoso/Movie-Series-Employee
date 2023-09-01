package com.example.demo.domain.dto.response;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageTmdb {
    private int page;
    private List<LinkedHashMap> results = new ArrayList<>();
    private int total_pages;
    private int total_results;
}
