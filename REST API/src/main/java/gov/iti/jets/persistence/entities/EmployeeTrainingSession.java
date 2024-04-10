package gov.iti.jets.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "training_employee_session")
public class EmployeeTrainingSession {
    @Id
    @Embedded
    private TrainingSessionEmployeeId id;

    @ManyToOne
    @JoinColumn(name = "training_id",  insertable = false, updatable = false)
    private TrainingSession trainingSession;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

}
