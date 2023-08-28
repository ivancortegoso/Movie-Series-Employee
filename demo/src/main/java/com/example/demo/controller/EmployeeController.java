package com.example.demo.controller;

import com.example.demo.domain.dto.response.EmployeeResponse;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(@PageableDefault(size = 50) final Pageable pageable) {
        Page<EmployeeResponse> employeeResponses = employeeService.findAllEmployees(pageable);
        return ResponseEntity.ok(employeeResponses);
    }

}
