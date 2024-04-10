package gov.iti.jets;

import gov.iti.jets.persistence.DTOs.DepartmentDTO;
import org.junit.jupiter.api.Test;

public class DepartmentControllerTestClass extends AbstractTestClass {

    @Override
    protected String getEndpoint() {
        return "/department";
    }

    @Test
    public void testCreateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("PR & Consultation");
        testCreate(departmentDTO);
    }

    @Test
    public void testGetAllDepartments() {
        testGetAll(1, 5);
    }

    @Test
    public void testGetDepartmentById() {
        testGetOne(1);
    }

    @Test
    public void testUpdateDepartment() {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("Marketing & Sales");
        testUpdate(1, departmentDTO);
    }

    @Test
    public void testDeleteDepartment() {
        testDelete(2);
    }
}