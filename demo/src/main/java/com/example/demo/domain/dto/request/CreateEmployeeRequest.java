package com.example.demo.domain.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int phoneNumber;
    private String email;
    private String password;
}
