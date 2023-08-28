package com.example.demo.controller;

import com.example.demo.domain.dto.LoginDto;
import com.example.demo.domain.dto.exception.ApiErrorException;
import com.example.demo.domain.dto.request.CreateEmployeeRequest;
import com.example.demo.domain.dto.response.EmployeeResponse;
import com.example.demo.domain.dto.response.JWTAuthResponse;
import com.example.demo.domain.mapper.EmployeeMapper;
import com.example.demo.service.AuthServiceImpl;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/public/")
@Tag(name = "Authentication", description = "API authentication functions, register and login")
public class AuthenticationController {
    @Autowired
    protected EmployeeService userService;
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    EmployeeMapper mapper;

    // Build Login REST API
    @PostMapping("login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
    @PostMapping(path = "register")
    public ResponseEntity<EmployeeResponse> register(@RequestBody CreateEmployeeRequest request) throws ApiErrorException {
        if (userService.findByEmail(request.getEmail()) != null) {
            throw new ApiErrorException("Email already exists!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }
}
