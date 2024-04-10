package gov.iti.jets.persistence.DTOs;

import gov.iti.jets.persistence.utils.enums.Gender;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.ws.rs.core.Link;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonbPropertyOrder({"employeeId", "firstName", "lastName", "email", "phone", "dateOfBirth", "gender", "address", "hireDate", "departmentID", "jobTitleID", "managerID"})
@Data
public class EmployeeDTO implements Serializable {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
    private String phone;
    private String address;
    private Date hireDate;
    private Integer departmentID;
    private Integer jobTitleID;
    private Integer managerID;
}
