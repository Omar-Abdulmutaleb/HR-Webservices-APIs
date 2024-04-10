package gov.iti.jets.persistence.DAOs.GenericDAOs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public abstract class GenericDAOImpl<T> implements GenericDAOInt<T> {
    protected Class<T> persistentClass;
    protected final EntityManager entityManager;

    public GenericDAOImpl(Class<T> persistentClass, EntityManager entityManager) {
        this.persistentClass = persistentClass;
       this.entityManager = entityManager;
    }
//
//    public List<T> findAll() {
//        return entityManager.createQuery("from " + persistentClass.getName(), persistentClass)
//                .getResultList();
//    }

    public List<T> findAll(Integer page, Integer size) {
        // Calculate the start index for the records
        int startIndex = (page - 1) * size;

        // Create a query to fetch 'size' number of records starting from the 'startIndex'
        Query query = entityManager.createQuery("SELECT e FROM " + persistentClass.getName() + " e", persistentClass);
        query.setFirstResult(startIndex);
        query.setMaxResults(size);

        // Execute the query and return the fetched records
        return query.getResultList();
    }


    public T findById(Integer id) {
        return entityManager.find(persistentClass, id);
    }

    public Optional<T> findByIdOptional(Integer id) {
        T entity = entityManager.find(persistentClass, id);
        return Optional.ofNullable(entity);
    }

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void deleteById(int id) {
        T entity = findById(id);
        delete(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }



}
