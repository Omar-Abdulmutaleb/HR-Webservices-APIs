package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.JobTitleDTO;
import gov.iti.jets.services.JobTitleService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public class JobTitleController {

    private final JobTitleService jobTitleService = new JobTitleService();

    @WebMethod
    public JobTitleDTO createJobTitle(JobTitleDTO jobTitleDTO) {
        return jobTitleService.createJobTitle(jobTitleDTO);
    }

    @WebMethod
    public List<JobTitleDTO> getAllJobTitles(Integer page, Integer size) {
        return jobTitleService.getAllJobTitles(page, size);
    }

    @WebMethod
    public JobTitleDTO getJobTitleById(Integer id) {
        return jobTitleService.getJobTitleById(id);
    }

    @WebMethod
    public JobTitleDTO getJobTitleByName(String name) {
        return jobTitleService.getJobTitleByName(name);
    }

    @WebMethod
    public void updateJobTitle(Integer id, JobTitleDTO jobTitleDTO) {
        if (jobTitleDTO.getJobTitleId() == null) {
            jobTitleDTO.setJobTitleId(id);
        }
        jobTitleService.updateJobTitle(jobTitleDTO);
    }

    @WebMethod
    public void deleteJobTitleById(Integer id) {
        jobTitleService.deleteJobTitleById(id);
    }
}
