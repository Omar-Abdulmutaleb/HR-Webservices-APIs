package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.AttendanceRecordDTO;
import gov.iti.jets.services.AttendanceRecordService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;


import java.util.List;

@WebService
public class AttendanceRecordController {

    private AttendanceRecordService attendanceRecordService = new AttendanceRecordService();

    @WebMethod
    public AttendanceRecordDTO createAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
        return attendanceRecordService.createAttendanceRecord(attendanceRecordDTO);
    }

    @WebMethod
    public List<AttendanceRecordDTO> getAllAttendanceRecords(Integer page, Integer size) {
        return attendanceRecordService.getAllAttendanceRecords(page, size);
    }

    @WebMethod
    public AttendanceRecordDTO getAttendanceRecordById(Integer id) {
        return attendanceRecordService.getAttendanceRecordById(id);
    }

    @WebMethod
    public AttendanceRecordDTO checkoutAttendance(Integer id) {
        return attendanceRecordService.checkoutAttendance(id);
    }

    @WebMethod
    public List<AttendanceRecordDTO> getAttendanceRecordByEmployeeId(Integer id) {
        return attendanceRecordService.getAttendanceRecordByEmployeeId(id);
    }

    @WebMethod
    public List<AttendanceRecordDTO> getAttendanceRecordsByDay(String day) {
        return attendanceRecordService.getAttendanceRecordsByDay(day);
    }

    @WebMethod
    public void deleteAttendanceRecordById(Integer id) {
        attendanceRecordService.deleteAttendanceRecordById(id);
    }
}
