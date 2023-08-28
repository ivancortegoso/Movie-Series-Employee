package com.example.demo.domain.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiErrorResponse {
    private List<ApiError> errors = new ArrayList<>();
}