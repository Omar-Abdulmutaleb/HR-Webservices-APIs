package gov.iti.jets;

import gov.iti.jets.persistence.DTOs.AttendanceRecordDTO;
import jakarta.ws.rs.client.Entity;
import org.junit.jupiter.api.Test;

public class AttendanceRecordControllerTestClass extends AbstractTestClass {

    @Override
    protected String getEndpoint() {
        return "/attendance";
    }

    @Test
    public void testCreateAttendanceRecord() {
        AttendanceRecordDTO attendanceRecordDTO = new AttendanceRecordDTO();
        attendanceRecordDTO.setEmployeeId(1);
        testCreate(attendanceRecordDTO);
    }

    @Test
    public void testGetAllAttendanceRecords() {
        testGetAll(1, 5);
    }

    @Test
    public void testGetAttendanceRecordById() {
        testGetOne(4);
    }

    @Test
    public void testDeleteAttendanceRecord() {
        testDelete(2); 
    }
}