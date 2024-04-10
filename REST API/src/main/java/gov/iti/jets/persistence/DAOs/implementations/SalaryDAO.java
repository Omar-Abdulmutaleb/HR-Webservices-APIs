package gov.iti.jets.persistence.DAOs.implementations;

import gov.iti.jets.persistence.DAOs.GenericDAOs.GenericDAOImpl;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.Salary;
import jakarta.persistence.EntityManager;

public class SalaryDAO extends GenericDAOImpl<Salary> {
    public SalaryDAO(EntityManager entityManager) {
        super(Salary.class, entityManager);
    }

    public Salary findByEmployeeId(Integer id) {
        return entityManager.createQuery("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId", Salary.class)
                .setParameter("employeeId", id)
                .getSingleResult();
    }

}
