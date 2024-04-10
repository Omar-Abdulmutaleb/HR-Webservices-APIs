package gov.iti.jets.persistence.DTOs;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonbPropertyOrder({"salaryId", "employeeId", "grossSalary", "deductions", "netSalary"})
public class SalaryDTO implements Serializable {

    private Integer salaryId;
    private Integer employeeId;
    //private BigDecimal salaryAmount;
    private BigDecimal deductions;
    private BigDecimal grossSalary;
    private BigDecimal netSalary;
}
