package gov.iti.jets.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class TrainingSessionEmployeeId {

    @Column(name = "training_id")
    private Integer trainingId;

    @Column(name = "employee_id")
    private Integer employeeId;

}
