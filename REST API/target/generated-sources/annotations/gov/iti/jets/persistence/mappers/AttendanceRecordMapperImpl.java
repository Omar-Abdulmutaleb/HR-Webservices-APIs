package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.AttendanceRecordDTO;
import gov.iti.jets.persistence.entities.AttendanceRecord;
import gov.iti.jets.persistence.entities.Employee;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T18:42:16+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class AttendanceRecordMapperImpl implements AttendanceRecordMapper {

    @Override
    public AttendanceRecordDTO toDto(AttendanceRecord attendanceRecord) {
        if ( attendanceRecord == null ) {
            return null;
        }

        AttendanceRecordDTO attendanceRecordDTO = new AttendanceRecordDTO();

        attendanceRecordDTO.setEmployeeId( attendanceRecordEmployeeEmployeeId( attendanceRecord ) );
        attendanceRecordDTO.setEmployeeFirstName( attendanceRecordEmployeeFirstName( attendanceRecord ) );
        attendanceRecordDTO.setEmployeeLastName( attendanceRecordEmployeeLastName( attendanceRecord ) );
        attendanceRecordDTO.setAttendanceId( attendanceRecord.getAttendanceId() );
        attendanceRecordDTO.setTimeIn( attendanceRecord.getTimeIn() );
        attendanceRecordDTO.setTimeOut( attendanceRecord.getTimeOut() );

        return attendanceRecordDTO;
    }

    @Override
    public AttendanceRecord toEntity(AttendanceRecordDTO attendanceRecordDTO) {
        if ( attendanceRecordDTO == null ) {
            return null;
        }

        AttendanceRecord attendanceRecord = new AttendanceRecord();

        attendanceRecord.setEmployee( attendanceRecordDTOToEmployee( attendanceRecordDTO ) );
        attendanceRecord.setAttendanceId( attendanceRecordDTO.getAttendanceId() );
        attendanceRecord.setTimeIn( attendanceRecordDTO.getTimeIn() );
        attendanceRecord.setTimeOut( attendanceRecordDTO.getTimeOut() );

        return attendanceRecord;
    }

    private Integer attendanceRecordEmployeeEmployeeId(AttendanceRecord attendanceRecord) {
        if ( attendanceRecord == null ) {
            return null;
        }
        Employee employee = attendanceRecord.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Integer employeeId = employee.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        return employeeId;
    }

    private String attendanceRecordEmployeeFirstName(AttendanceRecord attendanceRecord) {
        if ( attendanceRecord == null ) {
            return null;
        }
        Employee employee = attendanceRecord.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String firstName = employee.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String attendanceRecordEmployeeLastName(AttendanceRecord attendanceRecord) {
        if ( attendanceRecord == null ) {
            return null;
        }
        Employee employee = attendanceRecord.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String lastName = employee.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    protected Employee attendanceRecordDTOToEmployee(AttendanceRecordDTO attendanceRecordDTO) {
        if ( attendanceRecordDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmployeeId( attendanceRecordDTO.getEmployeeId() );
        employee.setFirstName( attendanceRecordDTO.getEmployeeFirstName() );
        employee.setLastName( attendanceRecordDTO.getEmployeeLastName() );

        return employee;
    }
}
