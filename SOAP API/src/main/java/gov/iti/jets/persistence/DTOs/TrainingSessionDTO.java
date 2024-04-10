package gov.iti.jets.persistence.DTOs;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonbPropertyOrder({"sessionId", "trainingName", "startDate", "endDate", "location"})
public class TrainingSessionDTO implements Serializable {

    private Integer sessionId;

    private String trainingName;

    private Integer employeeId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String location;


}
