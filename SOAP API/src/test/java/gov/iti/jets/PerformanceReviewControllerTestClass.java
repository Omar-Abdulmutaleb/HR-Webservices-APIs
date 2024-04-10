package gov.iti.jets;

import gov.iti.jets.persistence.DTOs.PerformanceReviewDTO;
import org.junit.jupiter.api.Test;

public class PerformanceReviewControllerTestClass extends AbstractTestClass {

    @Override
    protected String getEndpoint() {
        return "/review";
    }


    @Test
    public void testCreatePerformanceReview() {
        PerformanceReviewDTO performanceReviewDTO = new PerformanceReviewDTO();
        performanceReviewDTO.setEmployeeId(1);
        performanceReviewDTO.setReviewerId(2);
        performanceReviewDTO.setRating(5);
        testCreate(performanceReviewDTO);
    }

    @Test
    public void testGetAllPerformanceReviews() {
        testGetAll(1, 5);
    }

    @Test
    public void testGetPerformanceReviewById() {
        testGetOne(1);
    }

    @Test
    public void testUpdatePerformanceReview() {
        PerformanceReviewDTO performanceReviewDTO = new PerformanceReviewDTO();
        performanceReviewDTO.setRating(2);
        testUpdate(1, performanceReviewDTO);
    }

    @Test
    public void testDeletePerformanceReview() {
        testDelete(2);
    }
}