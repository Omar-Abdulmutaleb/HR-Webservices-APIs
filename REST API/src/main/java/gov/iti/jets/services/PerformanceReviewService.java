package gov.iti.jets.services;

import gov.iti.jets.persistence.DAOs.implementations.PerformanceReviewDAO;
import gov.iti.jets.persistence.DTOs.PerformanceReviewDTO;
import gov.iti.jets.persistence.entities.PerformanceReview;
import gov.iti.jets.persistence.mappers.PerformanceReviewMapper;
import gov.iti.jets.persistence.utils.EntityUpdateUtil;
import gov.iti.jets.utils.JPATransactionManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PerformanceReviewService {

    public PerformanceReviewDTO createPerformanceReview(PerformanceReviewDTO performanceReviewDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(em);
            PerformanceReview performanceReview = PerformanceReviewMapper.INSTANCE.toEntity(performanceReviewDTO);
            performanceReview.setReviewDate(LocalDateTime.now());
            performanceReviewDAO.create(performanceReview);
            return PerformanceReviewMapper.INSTANCE.toDto(performanceReview);
        });
    }

    public List<PerformanceReviewDTO> getAllPerformanceReviews(Integer page, Integer size) {
        return JPATransactionManager.doInTransaction(em -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(em);
            List<PerformanceReview> performanceReviews = performanceReviewDAO.findAll(page, size);
            return performanceReviews.stream()
                    .map(PerformanceReviewMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }

    public PerformanceReviewDTO getPerformanceReviewById(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(em);
            PerformanceReview performanceReview = performanceReviewDAO.findById(id);
            return PerformanceReviewMapper.INSTANCE.toDto(performanceReview);
        });
    }

    public PerformanceReviewDTO updatePerformanceReview(PerformanceReviewDTO performanceReviewDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(em);
            if (performanceReviewDTO.getReviewId() == null) {
                throw new IllegalArgumentException("Review ID cannot be null");
            }
            PerformanceReview existingPerformanceReview = performanceReviewDAO.findById(performanceReviewDTO.getReviewId());
            if (existingPerformanceReview != null) {
                EntityUpdateUtil.copyNonNullProperties(performanceReviewDTO, existingPerformanceReview);
                performanceReviewDAO.update(existingPerformanceReview);
            } else {
                throw new IllegalArgumentException("Performance Review not found");
            }
            return PerformanceReviewMapper.INSTANCE.toDto(existingPerformanceReview);
        });
    }

    public void deletePerformanceReviewById(Integer id) {
        JPATransactionManager.doInTransaction(em -> {
            PerformanceReviewDAO performanceReviewDAO = new PerformanceReviewDAO(em);
            performanceReviewDAO.deleteById(id);
            return null;
        });
    }
}