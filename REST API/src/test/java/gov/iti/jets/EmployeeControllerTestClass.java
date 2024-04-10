package gov.iti.jets;

import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.persistence.utils.enums.Gender;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeControllerTestClass extends AbstractTestClass {

    @Override
    protected String getEndpoint() {
        return "/employee";
    }

    @Test
    public void testCreateEmployee() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = formatter.parse("2024-04-04T22:00:00Z");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        employeeDTO.setDateOfBirth(date);
        employeeDTO.setGender(Gender.Male);
        employeeDTO.setEmail("john.doe@example.com");
        employeeDTO.setPhone("1234567890");
        employeeDTO.setAddress("123 Main St");
        employeeDTO.setHireDate(date);
        employeeDTO.setDepartmentID(1);
        employeeDTO.setJobTitleID(1);
        employeeDTO.setManagerID(1);
        testCreate(employeeDTO);
    }

    @Test
    public void testGetAllEmployees() {
        testGetAll(1, 5);
    }

    @Test
    public void testGetEmployeeById() {
        testGetOne(1); // replace 1 with the id of the employee you want to test
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName("John");
        employeeDTO.setLastName("Doe");
        testUpdate(1, employeeDTO); // replace 1 with the id of the employee you want to test
    }

    @Test
    public void testDeleteEmployee() {
        testDelete(2); // replace 1 with the id of the employee you want to test
    }
}