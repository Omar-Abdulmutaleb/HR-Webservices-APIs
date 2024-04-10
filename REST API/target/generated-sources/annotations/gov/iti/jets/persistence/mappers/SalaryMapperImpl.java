package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.SalaryDTO;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.Salary;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T18:42:16+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class SalaryMapperImpl implements SalaryMapper {

    @Override
    public Salary toEntity(SalaryDTO salaryDTO) {
        if ( salaryDTO == null ) {
            return null;
        }

        Salary salary = new Salary();

        salary.setEmployee( salaryDTOToEmployee( salaryDTO ) );
        salary.setSalaryId( salaryDTO.getSalaryId() );
        salary.setDeductions( salaryDTO.getDeductions() );
        salary.setGrossSalary( salaryDTO.getGrossSalary() );
        salary.setNetSalary( salaryDTO.getNetSalary() );

        return salary;
    }

    @Override
    public SalaryDTO toDto(Salary salary) {
        if ( salary == null ) {
            return null;
        }

        SalaryDTO salaryDTO = new SalaryDTO();

        salaryDTO.setEmployeeId( salaryEmployeeEmployeeId( salary ) );
        salaryDTO.setSalaryId( salary.getSalaryId() );
        salaryDTO.setDeductions( salary.getDeductions() );
        salaryDTO.setGrossSalary( salary.getGrossSalary() );
        salaryDTO.setNetSalary( salary.getNetSalary() );

        return salaryDTO;
    }

    protected Employee salaryDTOToEmployee(SalaryDTO salaryDTO) {
        if ( salaryDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmployeeId( salaryDTO.getEmployeeId() );

        return employee;
    }

    private Integer salaryEmployeeEmployeeId(Salary salary) {
        if ( salary == null ) {
            return null;
        }
        Employee employee = salary.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Integer employeeId = employee.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        return employeeId;
    }
}
