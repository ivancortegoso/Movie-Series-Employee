package com.example.demo.repository;

import com.example.demo.domain.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
