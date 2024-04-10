package gov.iti.jets.services;

import gov.iti.jets.persistence.DAOs.implementations.EmployeeDAO;
import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.mappers.EmployeeMapper;
import gov.iti.jets.persistence.utils.EntityUpdateUtil;
import gov.iti.jets.utils.JPATransactionManager;
import gov.iti.jets.utils.exceptionBuilder.BusinessException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        System.out.println("EmployeeService.createEmployee " + employeeDTO.toString());
        if (employeeDTO.getManagerID() == null) {
            throw new BusinessException(Response.Status.BAD_REQUEST.getReasonPhrase(), Response.Status.BAD_REQUEST.getStatusCode()
                    , "Manager ID cannot be null");
        } else if (employeeDTO.getJobTitleID() == null) {
            throw new BusinessException(Response.Status.BAD_REQUEST.getReasonPhrase(), Response.Status.BAD_REQUEST.getStatusCode()
                    , "Job title cannot be null");
        } else if (employeeDTO.getDepartmentID() == null) {
            throw new BusinessException(Response.Status.BAD_REQUEST.getReasonPhrase(), Response.Status.BAD_REQUEST.getStatusCode()
                    , "Department ID cannot be null");
        }

        return JPATransactionManager.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(em);
            Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
            return EmployeeMapper.INSTANCE.toDto((Employee) employeeDAO.create(employee));
        });
    }


    public List<EmployeeDTO> getAllEmployees(Integer page, Integer size) {
        return JPATransactionManager.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(em);
            List<Employee> employees = employeeDAO.findAll(page, size);
            return employees.stream()
                    .map(EmployeeMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }

    public EmployeeDTO getEmployeeById(Integer employeeId) {
        return JPATransactionManager.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(em);
            Optional<Employee> employee =  employeeDAO.findByIdOptional(employeeId);
            if (!employee.isPresent()) {
                throw new BusinessException(Response.Status.NOT_FOUND.getReasonPhrase(), Response.Status.NOT_FOUND.getStatusCode()
                        , "Employee not found for ID: " + employeeId);
            }
            return EmployeeMapper.INSTANCE.toDto(employee.get());
        });
    }

    public List<EmployeeDTO> getEmployeesByJobTitle(String jobTitle) {
        return JPATransactionManager.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(em);
            List<Employee> employees = employeeDAO.findByJobTitle(jobTitle);
            return employees.stream()
                    .map(EmployeeMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }




    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(em);
            if (employeeDTO.getEmployeeId() == null) {
                throw new IllegalArgumentException("Employee ID cannot be null");
            }
            Employee existingEmployee = (Employee) employeeDAO.findById(employeeDTO.getEmployeeId());
            if (existingEmployee != null) {
                EntityUpdateUtil.copyNonNullProperties(employeeDTO, existingEmployee);
                employeeDAO.update(existingEmployee);
            }

            return EmployeeMapper.INSTANCE.toDto(existingEmployee);
        });
    }

    public void deleteEmployeeById(Integer employeeId) {
        JPATransactionManager.doInTransaction(em -> {
            EmployeeDAO employeeDAO = new EmployeeDAO(em);
            Employee employee = (Employee) employeeDAO.findById(employeeId);
            if (employee != null) {
                // Fetch all employees where this employee is the manager
                List<Employee> subordinates = employeeDAO.findByManagerId(employeeId);
                for (Employee subordinate : subordinates) {
                    // Set manager to null
                    subordinate.setManager(null);
                    employeeDAO.update(subordinate);
                }
                // Delete the Employee
                employeeDAO.delete(employee);
            }
            return null;
        });
    }
}
