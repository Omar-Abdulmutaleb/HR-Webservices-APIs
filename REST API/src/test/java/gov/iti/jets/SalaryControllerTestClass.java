package gov.iti.jets;

import gov.iti.jets.persistence.DTOs.SalaryDTO;
import jakarta.ws.rs.client.Entity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SalaryControllerTestClass extends AbstractTestClass {

    @Override
    protected String getEndpoint() {
        return "/salary";
    }


    @Test
    public void testCreateSalary() {
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setEmployeeId(1);
        salaryDTO.setDeductions(BigDecimal.valueOf(1000));
        salaryDTO.setGrossSalary(BigDecimal.valueOf(10000));
        testCreate(salaryDTO);
    }

    @Test
    public void testGetAllSalaries() {
        testGetAll(1, 5);
    }

    @Test
    public void testGetSalaryById() {
        testGetOne(1); // replace 1 with the id of the salary you want to test
    }

    @Test
    public void testUpdateSalary() {
        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setDeductions(BigDecimal.valueOf(1000));
        salaryDTO.setGrossSalary(BigDecimal.valueOf(10000));
        // set the properties of salaryDTO...
        testUpdate(1, salaryDTO); // replace 10 with the id of the salary you want to test
    }

    @Test
    public void testDeleteSalary() {
        testDelete(2); // replace 10 with the id of the salary you want to test
    }


}