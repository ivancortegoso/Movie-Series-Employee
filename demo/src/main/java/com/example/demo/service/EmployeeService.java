package com.example.demo.service;

import com.example.demo.domain.dto.request.CreateEmployeeRequest;
import com.example.demo.domain.dto.response.EmployeeResponse;
import com.example.demo.domain.mapper.EmployeeMapper;
import com.example.demo.domain.model.Employee;
import com.example.demo.repository.IEmployeeRepository;
import com.example.demo.repository.IRoleRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService extends CommonService<Employee, IEmployeeRepository> implements UserDetailsService {
    @Autowired
    protected EmployeeMapper mapper;
    @Autowired
    protected MyUserDetailsService myUserDetailsService;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected IRoleRepository roleRepository;
    public Page<EmployeeResponse> findAllEmployees(Pageable pageable) {
        Page<Employee> employees = repository.findAll(pageable);
        return employees.map(EmployeeResponse::new);
    }

    @Transactional(readOnly = true)
    public Employee loadUserByUsername(String username) {
        return repository.findByEmailAndEnabled(username, true)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s, not found", username)));
    }

    public Employee findByEmail(String email) {
        return  repository.findByEmailAndEnabled(email, true).orElse(null);
    }

    public EmployeeResponse create(CreateEmployeeRequest request) {
        Employee employee = mapper.toEmployee(request);
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        employee = repository.save(employee);
        return mapper.toEmployeeResponse(employee);
    }

    public Employee getLogged() {
        Employee userLogged = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        myUserDetailsService.loadUserByUsername(userLogged.getUsername());
        return userLogged;
    }

}
