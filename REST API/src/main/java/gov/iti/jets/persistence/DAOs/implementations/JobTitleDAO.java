package gov.iti.jets.persistence.DAOs.implementations;

import gov.iti.jets.persistence.DAOs.GenericDAOs.GenericDAOImpl;
import gov.iti.jets.persistence.entities.JobTitle;
import jakarta.persistence.EntityManager;

public class JobTitleDAO extends GenericDAOImpl<JobTitle> {

    public JobTitleDAO(EntityManager entityManager) {
        super(JobTitle.class, entityManager);
    }

    public JobTitle getJobTitleByName(String name) {
        return entityManager.createQuery("SELECT j FROM JobTitle j WHERE j.title = :name", JobTitle.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
