package com.example.demo.domain.dto.response;

import com.example.demo.domain.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    public EmployeeResponse(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.phoneNumber = employee.getPhoneNumber();
        this.email = employee.getEmail();
    }

    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
}
