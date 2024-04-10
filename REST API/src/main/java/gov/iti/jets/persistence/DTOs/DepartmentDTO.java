package gov.iti.jets.persistence.DTOs;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonbPropertyOrder({"departmentId", "departmentName"})
public class DepartmentDTO implements Serializable {
    private Integer departmentId;
    private String departmentName;
}
