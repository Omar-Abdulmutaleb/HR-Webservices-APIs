package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.persistence.entities.Department;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.JobTitle;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T21:01:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setManager( employeeDTOToEmployee( employeeDTO ) );
        employee.setDepartment( employeeDTOToDepartment( employeeDTO ) );
        employee.setJobTitle( employeeDTOToJobTitle( employeeDTO ) );
        employee.setEmployeeId( employeeDTO.getEmployeeId() );
        employee.setFirstName( employeeDTO.getFirstName() );
        employee.setLastName( employeeDTO.getLastName() );
        employee.setDateOfBirth( employeeDTO.getDateOfBirth() );
        employee.setGender( employeeDTO.getGender() );
        employee.setEmail( employeeDTO.getEmail() );
        employee.setPhone( employeeDTO.getPhone() );
        employee.setAddress( employeeDTO.getAddress() );
        employee.setHireDate( employeeDTO.getHireDate() );

        return employee;
    }

    @Override
    public EmployeeDTO toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setManagerID( employeeManagerEmployeeId( employee ) );
        employeeDTO.setDepartmentID( employeeDepartmentDepartmentId( employee ) );
        employeeDTO.setJobTitleID( employeeJobTitleJobTitleId( employee ) );
        employeeDTO.setEmployeeId( employee.getEmployeeId() );
        employeeDTO.setFirstName( employee.getFirstName() );
        employeeDTO.setLastName( employee.getLastName() );
        employeeDTO.setDateOfBirth( employee.getDateOfBirth() );
        employeeDTO.setGender( employee.getGender() );
        employeeDTO.setEmail( employee.getEmail() );
        employeeDTO.setPhone( employee.getPhone() );
        employeeDTO.setAddress( employee.getAddress() );
        employeeDTO.setHireDate( employee.getHireDate() );

        return employeeDTO;
    }

    protected Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmployeeId( employeeDTO.getManagerID() );

        return employee;
    }

    protected Department employeeDTOToDepartment(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setDepartmentId( employeeDTO.getDepartmentID() );

        return department;
    }

    protected JobTitle employeeDTOToJobTitle(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        JobTitle jobTitle = new JobTitle();

        jobTitle.setJobTitleId( employeeDTO.getJobTitleID() );

        return jobTitle;
    }

    private Integer employeeManagerEmployeeId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Employee manager = employee.getManager();
        if ( manager == null ) {
            return null;
        }
        Integer employeeId = manager.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        return employeeId;
    }

    private Integer employeeDepartmentDepartmentId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Department department = employee.getDepartment();
        if ( department == null ) {
            return null;
        }
        Integer departmentId = department.getDepartmentId();
        if ( departmentId == null ) {
            return null;
        }
        return departmentId;
    }

    private Integer employeeJobTitleJobTitleId(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        JobTitle jobTitle = employee.getJobTitle();
        if ( jobTitle == null ) {
            return null;
        }
        Integer jobTitleId = jobTitle.getJobTitleId();
        if ( jobTitleId == null ) {
            return null;
        }
        return jobTitleId;
    }
}
