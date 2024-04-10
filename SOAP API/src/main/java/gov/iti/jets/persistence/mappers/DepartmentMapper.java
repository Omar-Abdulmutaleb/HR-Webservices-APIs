package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.DepartmentDTO;
import gov.iti.jets.persistence.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department toEntity(DepartmentDTO employeeDTO);

    DepartmentDTO toDto(Department employee);
}
