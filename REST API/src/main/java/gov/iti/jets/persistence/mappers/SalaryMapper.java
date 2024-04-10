package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.SalaryDTO;
import gov.iti.jets.persistence.entities.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SalaryMapper {
    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

     @Mapping(source = "employeeId", target = "employee.employeeId")
    Salary toEntity(SalaryDTO salaryDTO);

     @Mapping(source = "employee.employeeId", target = "employeeId")
     SalaryDTO toDto(Salary salary);
}

