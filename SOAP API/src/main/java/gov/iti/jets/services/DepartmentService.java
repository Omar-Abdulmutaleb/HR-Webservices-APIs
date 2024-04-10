package gov.iti.jets.services;

import gov.iti.jets.persistence.DAOs.implementations.DepartmentDAO;
import gov.iti.jets.persistence.DAOs.implementations.EmployeeDAO;
import gov.iti.jets.persistence.DTOs.DepartmentDTO;
import gov.iti.jets.persistence.entities.Department;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.mappers.DepartmentMapper;
import gov.iti.jets.persistence.mappers.EmployeeMapper;
import gov.iti.jets.persistence.utils.EntityUpdateUtil;
import gov.iti.jets.utils.JPATransactionManager;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentService {

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(em);
            Department department = DepartmentMapper.INSTANCE.toEntity(departmentDTO);
            departmentDAO.create(department);
            return DepartmentMapper.INSTANCE.toDto(department);
        });
    }

    public DepartmentDTO getDepartmentById(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(em);
            return DepartmentMapper.INSTANCE.toDto((Department) departmentDAO.findById(id));
        });
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(em);
            if (departmentDTO.getDepartmentId() == null) {
                throw new IllegalArgumentException("Department ID cannot be null");
            }
            Department existingDepartment = (Department) departmentDAO.findById(departmentDTO.getDepartmentId());
            if (existingDepartment != null) {
                EntityUpdateUtil.copyNonNullProperties(departmentDTO, existingDepartment);
                departmentDAO.update(existingDepartment);
            }
            return DepartmentMapper.INSTANCE.toDto(existingDepartment);
        });
    }

    public void deleteDepartmentById(Integer id) {
        JPATransactionManager.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(em);
            departmentDAO.deleteById(id);
            return null;
        });
    }

    public List<DepartmentDTO> getAllDepartments(Integer page, Integer size) {
        return JPATransactionManager.doInTransaction(em -> {
            DepartmentDAO departmentDAO = new DepartmentDAO(em);
            List<Department> departments = departmentDAO.findAll(page, size);
            return departments.stream()
                    .map(DepartmentMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }
}