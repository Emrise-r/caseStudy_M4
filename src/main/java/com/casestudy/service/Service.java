package com.casestudy.service;

import com.casestudy.exception.NotFoundException;

import java.util.Optional;

public interface Service<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id) throws NotFoundException;

    void save(T model);

    void remove(Long id);
}
