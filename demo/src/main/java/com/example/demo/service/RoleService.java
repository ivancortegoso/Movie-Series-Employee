package com.example.demo.service;

import com.example.demo.domain.model.Privilege;
import com.example.demo.domain.model.Role;
import com.example.demo.repository.IRoleRepository;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends CommonService<Role, IRoleRepository, Long>{
    @Transactional
    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = repository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            repository.save(role);
        }
        return role;
    }

    public Role findByName(String role) {
        return repository.findByName(role);
    }
}
