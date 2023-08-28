package com.example.demo.service;


import com.example.demo.domain.dto.LoginDto;

public interface IAuthService {
    String login(LoginDto loginDto);
}