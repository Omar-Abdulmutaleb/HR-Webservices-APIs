package gov.iti.jets.controllers;

import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ControllerInt<T> {
    default Response create(T entity) { return null; }
    default Response getAll(Integer page, Integer size) { return null; }
    default Response getOne(Integer id) { return null; }
    default Response update(Integer id, T entity) { return null; }
    default Response delete(Integer id) { return null; }
}
