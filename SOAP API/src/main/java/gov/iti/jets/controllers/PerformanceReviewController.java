package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.PerformanceReviewDTO;
import gov.iti.jets.services.PerformanceReviewService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public class PerformanceReviewController {

    private final PerformanceReviewService performanceReviewService = new PerformanceReviewService();

    @WebMethod
    public PerformanceReviewDTO createPerformanceReview(PerformanceReviewDTO performanceReviewDTO) {
        return performanceReviewService.createPerformanceReview(performanceReviewDTO);
    }

    @WebMethod
    public List<PerformanceReviewDTO> getAllPerformanceReviews(Integer page, Integer size) {
        return performanceReviewService.getAllPerformanceReviews(page, size);
    }

    @WebMethod
    public PerformanceReviewDTO getPerformanceReviewById(Integer id) {
        return performanceReviewService.getPerformanceReviewById(id);
    }

    @WebMethod
    public void updatePerformanceReview(Integer id, PerformanceReviewDTO performanceReviewDTO) {
        if (performanceReviewDTO.getReviewId() == null) {
            performanceReviewDTO.setReviewId(id);
        }
        performanceReviewService.updatePerformanceReview(performanceReviewDTO);
    }

    @WebMethod
    public void deletePerformanceReviewById(Integer id) {
        performanceReviewService.deletePerformanceReviewById(id);
    }
}
