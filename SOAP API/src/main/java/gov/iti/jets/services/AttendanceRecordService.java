package gov.iti.jets.services;

import gov.iti.jets.persistence.DAOs.implementations.AttendanceRecordDAO;
import gov.iti.jets.persistence.DTOs.AttendanceRecordDTO;
import gov.iti.jets.persistence.entities.AttendanceRecord;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.mappers.AttendanceRecordMapper;
import gov.iti.jets.persistence.utils.EntityUpdateUtil;
import gov.iti.jets.utils.JPATransactionManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceRecordService {


    public AttendanceRecordDTO createAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
            List<AttendanceRecord> records = attendanceRecordDAO.findByEmployeeId(attendanceRecordDTO.getEmployeeId());
            if (!records.isEmpty()) {
                AttendanceRecord lastRecord = records.get(records.size() - 1);
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime lastRecordTime = lastRecord.getTimeIn();

                if (lastRecordTime.plusHours(24).isAfter(now)) {
                    throw new IllegalStateException("Cannot add new record until 24 hours have passed since the last record");
                }
            }
            AttendanceRecord attendanceRecord = AttendanceRecordMapper.INSTANCE.toEntity(attendanceRecordDTO);
            attendanceRecord.setTimeIn(LocalDateTime.now()); // Set timeIn to the current timestamp
            attendanceRecordDAO.create(attendanceRecord);
            return AttendanceRecordMapper.INSTANCE.toDto(attendanceRecord);
        });
    }

    public AttendanceRecordDTO checkoutAttendance(Integer employeeId) {
        return JPATransactionManager.doInTransaction(em -> {
            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
            List<AttendanceRecord> records = attendanceRecordDAO.findByEmployeeId(employeeId);
            if (records.isEmpty()) {
                throw new IllegalStateException("No attendance record found for the employee");
            }
            AttendanceRecord lastRecord = records.get(records.size() - 1);
            if (lastRecord.getTimeOut() != null) {
                throw new IllegalStateException("The employee has already checked out");
            }
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime timeIn = lastRecord.getTimeIn();
            if (timeIn.plusSeconds(2).isAfter(now)) {
                throw new IllegalStateException("Cannot check out until 8 hours have passed since check in");
            }
            lastRecord.setTimeOut(now);
            attendanceRecordDAO.update(lastRecord);
            return AttendanceRecordMapper.INSTANCE.toDto(lastRecord);
        });
    }

    public AttendanceRecordDTO getAttendanceRecordById(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
            AttendanceRecord attendanceRecord = (AttendanceRecord) attendanceRecordDAO.findById(id);
            return AttendanceRecordMapper.INSTANCE.toDto(attendanceRecord);
        });
    }

//    public AttendanceRecordDTO updateAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
//        return JPATransactionManager.doInTransaction(em -> {
//            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
//            if (attendanceRecordDTO.getAttendanceId() == null) {
//                throw new IllegalArgumentException("Attendance ID cannot be null");
//            }
//            AttendanceRecord existingAttendanceRecord = (AttendanceRecord) attendanceRecordDAO.findById(attendanceRecordDTO.getAttendanceId());
//            if (existingAttendanceRecord != null) {
//                EntityUpdateUtil.copyNonNullProperties(attendanceRecordDTO, existingAttendanceRecord);
//                attendanceRecordDAO.update(existingAttendanceRecord);
//            }
//            return AttendanceRecordMapper.INSTANCE.toDto(existingAttendanceRecord);
//        });
//    }

    public void deleteAttendanceRecordById(Integer id) {
        JPATransactionManager.doInTransaction(em -> {
            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
            attendanceRecordDAO.deleteById(id);
            return null;
        });
    }

    public List<AttendanceRecordDTO> getAttendanceRecordByEmployeeId(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
            List<AttendanceRecord> attendanceRecords = attendanceRecordDAO.findByEmployeeId(id);
            return attendanceRecords.stream()
                    .map(AttendanceRecordMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }


    public List<AttendanceRecordDTO> getAllAttendanceRecords(Integer page, Integer size) {
        return JPATransactionManager.doInTransaction(em -> {
            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
            List<AttendanceRecord> attendanceRecords = attendanceRecordDAO.findAll(page, size);
            return attendanceRecords.stream()
                    .map(AttendanceRecordMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }

    public List<AttendanceRecordDTO> getAttendanceRecordsByDay(String day) {
        return JPATransactionManager.doInTransaction(em -> {
            AttendanceRecordDAO attendanceRecordDAO = new AttendanceRecordDAO(em);
            List<AttendanceRecord> attendanceRecords = attendanceRecordDAO.findByDay(day);
            return attendanceRecords.stream()
                    .map(AttendanceRecordMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
        });
    }
}