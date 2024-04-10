package gov.iti.jets.services;

import gov.iti.jets.persistence.DAOs.implementations.SalaryDAO;
import gov.iti.jets.persistence.DTOs.SalaryDTO;
import gov.iti.jets.persistence.entities.Salary;
import gov.iti.jets.persistence.mappers.SalaryMapper;
import gov.iti.jets.persistence.utils.EntityUpdateUtil;
import gov.iti.jets.utils.JPATransactionManager;
import gov.iti.jets.utils.exceptionBuilder.BusinessException;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryService {

    public SalaryDTO createSalary(SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO(em);
            Salary salary = SalaryMapper.INSTANCE.toEntity(salaryDTO);
            salary.setNetSalary(salary.getGrossSalary().subtract(salary.getDeductions()));
            salaryDAO.create(salary);
            return SalaryMapper.INSTANCE.toDto(salary);
        });
    }

    public List<SalaryDTO> getAllSalaries(Integer page, Integer size) {
        return JPATransactionManager.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO(em);
            return salaryDAO.findAll(page, size).stream()
                    .map(SalaryMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }

    public SalaryDTO getSalaryById(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO(em);
            return SalaryMapper.INSTANCE.toDto(salaryDAO.findById(id));
        });
    }

    public SalaryDTO getSalaryByEmployeeId(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO(em);
            return SalaryMapper.INSTANCE.toDto(salaryDAO.findByEmployeeId(id));
        });
    }


    public SalaryDTO updateSalary(SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO(em);
            if (salaryDTO.getSalaryId() == null) {
                throw new IllegalArgumentException("Salary ID cannot be null");
            }
            BigDecimal netSalary = null;
            Salary existingSalary = salaryDAO.findById(salaryDTO.getSalaryId());
            if (existingSalary != null) {
                // Only update grossSalary and deductions
                if (salaryDTO.getGrossSalary() != null) {
                    existingSalary.setGrossSalary(salaryDTO.getGrossSalary());
                }
                if (salaryDTO.getDeductions() != null) {
                    existingSalary.setDeductions(salaryDTO.getDeductions());
                }

                // Calculate netSalary
                if (existingSalary.getGrossSalary() != null && existingSalary.getDeductions() != null) {
                     netSalary = existingSalary.getGrossSalary().subtract(existingSalary.getDeductions());
                    existingSalary.setNetSalary(netSalary);
                }
                System.out.println("netSalary = " + netSalary);

                salaryDAO.update(existingSalary);
                existingSalary = salaryDAO.findById(salaryDTO.getSalaryId());

            }
            existingSalary.setNetSalary(netSalary);
            return SalaryMapper.INSTANCE.toDto(existingSalary);
        });
    }

//    private void copyNonNullSalaryProperties(SalaryDTO source, Salary target) {
//        if (source.getSalaryAmount() != null) {
//            target.setSalaryAmount(source.getSalaryAmount());
//        }
//        if (source.getDeductions() != null) {
//            target.setDeductions(source.getDeductions());
//        }
//        // grossSalary and netSalary are not copied because they are calculated fields
//    }

    public void deleteSalaryById(Integer id) {
        JPATransactionManager.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO(em);
            salaryDAO.deleteById(id);
            return null;
        });
    }


    public SalaryDTO updateSalaryByEmployeeId(Integer id, SalaryDTO salaryDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO(em);
            Salary existingSalary = salaryDAO.findByEmployeeId(id);
            if (existingSalary == null) {
                throw new BusinessException(Response.Status.NOT_FOUND.getReasonPhrase(), Response.Status.NOT_FOUND.getStatusCode()
                        , "Employee with ID: " + id + " does not have a salary record");
            }
            if (salaryDTO.getGrossSalary() != null) {
                existingSalary.setGrossSalary(salaryDTO.getGrossSalary());
            }
            if (salaryDTO.getDeductions() != null) {
                existingSalary.setDeductions(salaryDTO.getDeductions());
            }
            if (existingSalary.getGrossSalary() != null && existingSalary.getDeductions() != null) {
                BigDecimal netSalary = existingSalary.getGrossSalary().subtract(existingSalary.getDeductions());
                existingSalary.setNetSalary(netSalary);
            }
            salaryDAO.update(existingSalary);
            return SalaryMapper.INSTANCE.toDto(existingSalary);
        });
    }
}