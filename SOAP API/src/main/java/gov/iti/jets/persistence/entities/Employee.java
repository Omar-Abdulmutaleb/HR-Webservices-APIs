package gov.iti.jets.persistence.entities;

import gov.iti.jets.persistence.utils.enums.Gender;
import jakarta.annotation.Nullable;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "salary") // Exclude salary from hashCode and equals
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "hire_date")
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_title_id")
    private JobTitle jobTitle;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Employee> subordinates;

    @JsonbTransient
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE,  fetch = FetchType.LAZY)
    private List<AttendanceRecord> attendanceRecords;

    @JsonbTransient
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE,  fetch = FetchType.LAZY)
    private List<PerformanceReview> performanceReviews;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PerformanceReview> reviewedPerformanceReviews;

    @JsonbTransient
    @OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE, optional = true, fetch = FetchType.LAZY)
    private Salary salary;

//    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private List<EmployeeTrainingSession> trainingSessions;

}
