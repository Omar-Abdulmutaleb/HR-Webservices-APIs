package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.DepartmentDTO;
import gov.iti.jets.persistence.entities.Department;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T18:42:17+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department toEntity(DepartmentDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setDepartmentId( employeeDTO.getDepartmentId() );
        department.setDepartmentName( employeeDTO.getDepartmentName() );

        return department;
    }

    @Override
    public DepartmentDTO toDto(Department employee) {
        if ( employee == null ) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setDepartmentId( employee.getDepartmentId() );
        departmentDTO.setDepartmentName( employee.getDepartmentName() );

        return departmentDTO;
    }
}
