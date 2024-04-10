package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.PerformanceReviewDTO;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.PerformanceReview;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T18:42:16+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class PerformanceReviewMapperImpl implements PerformanceReviewMapper {

    @Override
    public PerformanceReview toEntity(PerformanceReviewDTO performanceReviewDTO) {
        if ( performanceReviewDTO == null ) {
            return null;
        }

        PerformanceReview performanceReview = new PerformanceReview();

        performanceReview.setEmployee( performanceReviewDTOToEmployee( performanceReviewDTO ) );
        performanceReview.setReviewer( performanceReviewDTOToEmployee1( performanceReviewDTO ) );
        performanceReview.setReviewId( performanceReviewDTO.getReviewId() );
        performanceReview.setReviewDate( performanceReviewDTO.getReviewDate() );
        performanceReview.setRating( performanceReviewDTO.getRating() );

        return performanceReview;
    }

    @Override
    public PerformanceReviewDTO toDto(PerformanceReview performanceReview) {
        if ( performanceReview == null ) {
            return null;
        }

        PerformanceReviewDTO performanceReviewDTO = new PerformanceReviewDTO();

        performanceReviewDTO.setEmployeeId( performanceReviewEmployeeEmployeeId( performanceReview ) );
        performanceReviewDTO.setReviewerId( performanceReviewReviewerEmployeeId( performanceReview ) );
        performanceReviewDTO.setReviewId( performanceReview.getReviewId() );
        performanceReviewDTO.setReviewDate( performanceReview.getReviewDate() );
        performanceReviewDTO.setRating( performanceReview.getRating() );

        return performanceReviewDTO;
    }

    protected Employee performanceReviewDTOToEmployee(PerformanceReviewDTO performanceReviewDTO) {
        if ( performanceReviewDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmployeeId( performanceReviewDTO.getEmployeeId() );

        return employee;
    }

    protected Employee performanceReviewDTOToEmployee1(PerformanceReviewDTO performanceReviewDTO) {
        if ( performanceReviewDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmployeeId( performanceReviewDTO.getReviewerId() );

        return employee;
    }

    private Integer performanceReviewEmployeeEmployeeId(PerformanceReview performanceReview) {
        if ( performanceReview == null ) {
            return null;
        }
        Employee employee = performanceReview.getEmployee();
        if ( employee == null ) {
            return null;
        }
        Integer employeeId = employee.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        return employeeId;
    }

    private Integer performanceReviewReviewerEmployeeId(PerformanceReview performanceReview) {
        if ( performanceReview == null ) {
            return null;
        }
        Employee reviewer = performanceReview.getReviewer();
        if ( reviewer == null ) {
            return null;
        }
        Integer employeeId = reviewer.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        return employeeId;
    }
}
