package gov.iti.jets;

import gov.iti.jets.persistence.DTOs.JobTitleDTO;
import org.junit.jupiter.api.Test;

public class JobTitleControllerTestClass extends AbstractTestClass {

    @Override
    protected String getEndpoint() {
        return "/job";
    }

    @Test
    public void testCreateJobTitle() {
        JobTitleDTO jobTitleDTO = new JobTitleDTO();
        jobTitleDTO.setTitle("Mechanical Engineer");
        testCreate(jobTitleDTO);
    }

    @Test
    public void testGetAllJobTitles() {
        testGetAll(1, 5);
    }

    @Test
    public void testGetJobTitleById() {
        testGetOne(1);
    }

    @Test
    public void testUpdateJobTitle() {
        JobTitleDTO jobTitleDTO = new JobTitleDTO();
        jobTitleDTO.setTitle("Software Engineer");
        testUpdate(1, jobTitleDTO);
    }

    @Test
    public void testDeleteJobTitle() {
        testDelete(2);
    }
}