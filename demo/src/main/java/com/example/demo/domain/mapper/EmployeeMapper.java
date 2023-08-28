package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.request.CreateEmployeeRequest;
import com.example.demo.domain.dto.response.EmployeeResponse;
import com.example.demo.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    public abstract EmployeeResponse toEmployeeResponse(Employee employees);
    @Mappings({
            @Mapping(target = "enabled", expression = "java(true)"),
            @Mapping(target = "tokenExpired", expression = "java(false)")
    })
    public abstract Employee toEmployee(CreateEmployeeRequest request);
}
