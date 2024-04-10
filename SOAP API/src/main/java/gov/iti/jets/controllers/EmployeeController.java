package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.services.EmployeeService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    @WebMethod
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @WebMethod
    public List<EmployeeDTO> getAllEmployees(Integer page, Integer size) {
        return employeeService.getAllEmployees(page, size);
    }

    @WebMethod
    public EmployeeDTO getEmployeeById(Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @WebMethod
    public List<EmployeeDTO> getEmployeesByJobTitle(String jobTitle) {
        return employeeService.getEmployeesByJobTitle(jobTitle);
    }

    @WebMethod
    public void updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        if (employeeDTO.getEmployeeId() == null) {
            employeeDTO.setEmployeeId(id);
        }
        employeeService.updateEmployee(employeeDTO);
    }

    @WebMethod
    public void deleteEmployeeById(Integer id) {
        employeeService.deleteEmployeeById(id);
    }
}
