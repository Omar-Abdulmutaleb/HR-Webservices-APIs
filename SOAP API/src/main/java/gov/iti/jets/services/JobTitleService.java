package gov.iti.jets.services;

import gov.iti.jets.persistence.DAOs.implementations.JobTitleDAO;
import gov.iti.jets.persistence.DTOs.JobTitleDTO;
import gov.iti.jets.persistence.entities.JobTitle;
import gov.iti.jets.persistence.mappers.JobTitleMapper;
import gov.iti.jets.persistence.utils.EntityUpdateUtil;
import gov.iti.jets.utils.JPATransactionManager;

import java.util.List;
import java.util.stream.Collectors;

public class JobTitleService {

    public JobTitleDTO createJobTitle(JobTitleDTO jobTitleDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            JobTitleDAO jobTitleDAO = new JobTitleDAO(em);
            JobTitle jobTitle = JobTitleMapper.INSTANCE.toEntity(jobTitleDTO);
            jobTitleDAO.create(jobTitle);
            return JobTitleMapper.INSTANCE.toDto(jobTitle);
        });
    }

    public JobTitleDTO getJobTitleById(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            JobTitleDAO jobTitleDAO = new JobTitleDAO(em);
            return JobTitleMapper.INSTANCE.toDto((JobTitle) jobTitleDAO.findById(id));
        });
    }

    public JobTitleDTO updateJobTitle(JobTitleDTO jobTitleDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            JobTitleDAO jobTitleDAO = new JobTitleDAO(em);
            if (jobTitleDTO.getJobTitleId() == null) {
                throw new IllegalArgumentException("Job title ID cannot be null");
            }
            JobTitle existingJobTitle = (JobTitle) jobTitleDAO.findById(jobTitleDTO.getJobTitleId());
            if (existingJobTitle != null) {
                EntityUpdateUtil.copyNonNullProperties(jobTitleDTO, existingJobTitle);
                jobTitleDAO.update(existingJobTitle);
            }
            return JobTitleMapper.INSTANCE.toDto(existingJobTitle);
        });
    }

    public void deleteJobTitleById(Integer id) {
        JPATransactionManager.doInTransaction(em -> {
            JobTitleDAO jobTitleDAO = new JobTitleDAO(em);
            jobTitleDAO.deleteById(id);
            return null;
        });
    }

    public List<JobTitleDTO> getAllJobTitles(Integer page, Integer size) {
        return JPATransactionManager.doInTransaction(em -> {
            JobTitleDAO jobTitleDAO = new JobTitleDAO(em);
            List<JobTitle> jobTitles = jobTitleDAO.findAll(page, size);
            return jobTitles.stream()
                    .map(JobTitleMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }

    public JobTitleDTO getJobTitleByName(String name) {
        return JPATransactionManager.doInTransaction(em -> {
            JobTitleDAO jobTitleDAO = new JobTitleDAO(em);
            JobTitle jobTitle = jobTitleDAO.getJobTitleByName(name);
            return JobTitleMapper.INSTANCE.toDto(jobTitle);
        });
    }
}