package com.bankapi.generic;

import com.bankapi.model.Response;

public interface GenericService<T extends Object> {

    T save(T entity);

    T update(T entity);

    void delete(String identifier);

    T find(String identifier);

    T[] findAll();
  

}
