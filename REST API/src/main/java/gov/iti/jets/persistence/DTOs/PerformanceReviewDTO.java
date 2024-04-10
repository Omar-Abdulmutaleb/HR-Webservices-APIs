package gov.iti.jets.persistence.DTOs;

import gov.iti.jets.persistence.entities.Employee;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

@JsonbPropertyOrder({"reviewId", "employeeId", "reviewDate", "reviewer", "rating"})
@Data
public class PerformanceReviewDTO implements Serializable {
    private Integer reviewId;
    private Integer employeeId;
    private LocalDateTime reviewDate;
    private Integer reviewerId;
    private Integer rating;

}
