package com.example.demo.repository;

import com.example.demo.domain.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmailAndEnabled(String email, boolean b);
}
