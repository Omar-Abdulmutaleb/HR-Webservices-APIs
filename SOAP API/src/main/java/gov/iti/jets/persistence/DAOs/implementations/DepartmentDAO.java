package gov.iti.jets.persistence.DAOs.implementations;

import gov.iti.jets.persistence.DAOs.GenericDAOs.GenericDAOImpl;
import gov.iti.jets.persistence.entities.Department;
import jakarta.persistence.EntityManager;

public class DepartmentDAO extends GenericDAOImpl<Department> {
    public DepartmentDAO(EntityManager entityManager) {

        super(Department.class, entityManager);
    }

}
