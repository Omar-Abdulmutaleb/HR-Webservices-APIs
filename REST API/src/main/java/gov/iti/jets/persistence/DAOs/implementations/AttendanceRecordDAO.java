package gov.iti.jets.persistence.DAOs.implementations;

import gov.iti.jets.persistence.DAOs.GenericDAOs.GenericDAOImpl;
import gov.iti.jets.persistence.entities.AttendanceRecord;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AttendanceRecordDAO extends GenericDAOImpl<AttendanceRecord> {
    public AttendanceRecordDAO(EntityManager entityManager) {
        super(AttendanceRecord.class, entityManager);
    }

    public List<AttendanceRecord> findByDay(String day) {
        return entityManager.createQuery("SELECT a FROM AttendanceRecord a WHERE CAST(a.timeIn AS string) LIKE :day", AttendanceRecord.class)
                .setParameter("day", day + "%")
                .getResultList();
    }

    public List<AttendanceRecord> findByEmployeeId(Integer id) {
        return entityManager.createQuery("SELECT a FROM AttendanceRecord a WHERE a.employee.employeeId = :id", AttendanceRecord.class)
                .setParameter("id", id)
                .getResultList();
    }
}
