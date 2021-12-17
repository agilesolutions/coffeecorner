package com.coffeecorner.repository;

import java.util.Optional;

public interface CrudRepository<T, ID> {

    Optional<T> save(T entity);

    Optional<T> findById(ID id);

    Iterable<T> findAll();

    void delete(T entity);

    void deleteById(ID id);

}
