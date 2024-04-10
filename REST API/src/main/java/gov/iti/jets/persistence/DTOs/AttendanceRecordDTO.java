package gov.iti.jets.persistence.DTOs;

import gov.iti.jets.persistence.entities.Employee;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonbPropertyOrder({"attendanceId", "employeeId", "employeeFirstName", "employeeLastName", "timeIn", "timeOut"})
public class AttendanceRecordDTO implements Serializable  {
    private Integer attendanceId;
    private Integer employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
}
