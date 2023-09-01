package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonService<E, R extends JpaRepository<E, K>, K> {
    @Autowired
    protected R repository;

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public E findById(K id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional()
    public E save(E entity) {
        return repository.save(entity);
    }

    @Transactional()
    public void deleteById(K id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }
}
