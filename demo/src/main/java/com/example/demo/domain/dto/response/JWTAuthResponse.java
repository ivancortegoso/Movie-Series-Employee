package com.example.demo.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
    String token;

    public void setAccessToken(String token) {
        this.token = token;
    }
}
