package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.DepartmentDTO;
import gov.iti.jets.services.DepartmentService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public class DepartmentController {

    private final DepartmentService departmentService = new DepartmentService();

    @WebMethod
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }

    @WebMethod
    public DepartmentDTO getDepartmentById(Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @WebMethod
    public List<DepartmentDTO> getAllDepartments(Integer page, Integer size) {
        return departmentService.getAllDepartments(page, size);
    }

    @WebMethod
    public void updateDepartment(Integer id, DepartmentDTO departmentDTO) {
        if (departmentDTO.getDepartmentId() == null) {
            departmentDTO.setDepartmentId(id);
        }
        departmentService.updateDepartment(departmentDTO);
    }

    @WebMethod
    public void deleteDepartmentById(Integer id) {
        departmentService.deleteDepartmentById(id);
    }
}
