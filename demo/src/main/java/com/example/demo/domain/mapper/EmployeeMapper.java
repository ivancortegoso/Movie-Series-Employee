package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.request.EmployeeRequest;
import com.example.demo.domain.dto.response.EmployeeResponse;
import com.example.demo.domain.model.Employee;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    public abstract EmployeeResponse toEmployeeResponse(Employee employees);

    @Mapping(target = "enabled", expression = "java(true)")
    @Mapping(target = "tokenExpired", expression = "java(false)")
    public abstract Employee toEmployee(EmployeeRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEmployeeRequestToEmployee(EmployeeRequest request, @MappingTarget Employee entity);
}
