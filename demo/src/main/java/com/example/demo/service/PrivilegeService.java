package com.example.demo.service;

import com.example.demo.domain.model.Privilege;
import com.example.demo.repository.IPrivilegeRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService extends CommonService<Privilege, IPrivilegeRepository>{
    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = repository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            repository.save(privilege);
        }
        return privilege;
    }
}
