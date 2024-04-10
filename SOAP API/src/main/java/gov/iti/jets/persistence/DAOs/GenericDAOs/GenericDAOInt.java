package gov.iti.jets.persistence.DAOs.GenericDAOs;

import jakarta.persistence.EntityManager;

import java.util.List;

public interface GenericDAOInt<T>{

    public List<T> findAll(Integer page, Integer pageSize);

    public T findById(Integer id);
    public T create(T entity);
    public T update(T entity);
    public void deleteById(int id);
    public void delete(T entity);
}
