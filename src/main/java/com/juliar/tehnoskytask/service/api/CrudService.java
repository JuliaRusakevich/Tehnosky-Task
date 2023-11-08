package com.juliar.tehnoskytask.service.api;

public interface CrudService<E, R> {

    R findById(Integer id);
}
