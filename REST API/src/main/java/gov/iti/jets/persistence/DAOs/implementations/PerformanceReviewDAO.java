package gov.iti.jets.persistence.DAOs.implementations;

import gov.iti.jets.persistence.DAOs.GenericDAOs.GenericDAOImpl;
import gov.iti.jets.persistence.entities.PerformanceReview;
import jakarta.persistence.EntityManager;

public class PerformanceReviewDAO extends GenericDAOImpl<PerformanceReview> {

    public PerformanceReviewDAO(EntityManager entityManager) {
        super(PerformanceReview.class, entityManager);
    }
}
