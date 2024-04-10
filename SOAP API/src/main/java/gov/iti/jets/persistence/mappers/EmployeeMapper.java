package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.persistence.entities.Employee;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mappings({
            @Mapping(source = "managerID", target = "manager.employeeId"),
            @Mapping(source = "departmentID", target = "department.departmentId"),
            @Mapping(source = "jobTitleID", target = "jobTitle.jobTitleId")
    })
    Employee toEntity(EmployeeDTO employeeDTO);
    @Mappings({
            @Mapping(source = "manager.employeeId", target = "managerID"),
            @Mapping(source = "department.departmentId", target = "departmentID"),
            @Mapping(source = "jobTitle.jobTitleId", target = "jobTitleID")
    })
    EmployeeDTO toDto(Employee employee);
}

