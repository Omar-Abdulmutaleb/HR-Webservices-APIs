package gov.iti.jets.persistence.DAOs.implementations;

import gov.iti.jets.persistence.DAOs.GenericDAOs.GenericDAOImpl;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.Salary;
import gov.iti.jets.persistence.entities.TrainingSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TrainingSessionDAO extends GenericDAOImpl<TrainingSession> {
    public TrainingSessionDAO(EntityManager entityManager) {
        super(TrainingSession.class, entityManager);
    }



    public List<Employee> findEmployeesByTrainingName(String trainingName) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT te.employee FROM EmployeeTrainingSession te " +
                        "JOIN te.trainingSession t " +
                        "WHERE t.trainingName = :trainingName", Employee.class);
        query.setParameter("trainingName", trainingName);
        return query.getResultList();
    }

    public TrainingSession findTrainingByName(String trainingName) {
        try {
            TypedQuery<TrainingSession> query = entityManager.createQuery(
                    "SELECT t FROM TrainingSession t " +
                            "WHERE t.trainingName = :trainingName", TrainingSession.class);
            query.setParameter("trainingName", trainingName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
