package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.AttendanceRecordDTO;
import gov.iti.jets.persistence.entities.AttendanceRecord;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendanceRecordMapper {
    AttendanceRecordMapper INSTANCE = Mappers.getMapper(AttendanceRecordMapper.class);

    @Mapping(source = "employee.employeeId", target = "employeeId")
    @Mapping(source = "employee.firstName", target = "employeeFirstName")
    @Mapping(source = "employee.lastName", target = "employeeLastName")
    AttendanceRecordDTO toDto(AttendanceRecord attendanceRecord);

    @InheritInverseConfiguration
    AttendanceRecord toEntity(AttendanceRecordDTO attendanceRecordDTO);
}
