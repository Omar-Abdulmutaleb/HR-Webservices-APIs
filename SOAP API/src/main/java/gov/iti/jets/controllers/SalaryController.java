package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.SalaryDTO;
import gov.iti.jets.services.SalaryService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public class SalaryController {

    private final SalaryService salaryService = new SalaryService();

    @WebMethod
    public SalaryDTO createSalary(SalaryDTO salaryDTO) {
        return salaryService.createSalary(salaryDTO);
    }

    @WebMethod
    public List<SalaryDTO> getAllSalaries(Integer page, Integer size) {
        return salaryService.getAllSalaries(page, size);
    }

    @WebMethod
    public SalaryDTO getSalaryById(Integer id) {
        return salaryService.getSalaryById(id);
    }

    @WebMethod
    public SalaryDTO getSalaryByEmployeeId(Integer id) {
        return salaryService.getSalaryByEmployeeId(id);
    }

    @WebMethod
    public SalaryDTO updateSalary(Integer id, SalaryDTO salaryDTO) {
        if (salaryDTO.getSalaryId() == null) {
            salaryDTO.setSalaryId(id);
        }
        return salaryService.updateSalary(salaryDTO);
    }

    @WebMethod
    public void deleteSalaryById(Integer id) {
        salaryService.deleteSalaryById(id);
    }
}
