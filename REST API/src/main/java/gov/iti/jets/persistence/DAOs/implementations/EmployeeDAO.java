package gov.iti.jets.persistence.DAOs.implementations;

import gov.iti.jets.persistence.DAOs.GenericDAOs.GenericDAOImpl;
import gov.iti.jets.persistence.entities.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EmployeeDAO extends GenericDAOImpl<Employee> {

    public EmployeeDAO(EntityManager entityManager) {
        super(Employee.class, entityManager);
    }

    public List<Employee> findByJobTitle(String jobTitle) {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.jobTitle.title = :jobTitle", Employee.class)
                .setParameter("jobTitle", jobTitle)
                .getResultList();
    }


    public List<Employee> findByManagerId(Integer employeeId) {
        return entityManager.createQuery("SELECT e FROM Employee e WHERE e.manager.employeeId = :employeeId", Employee.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }
}
