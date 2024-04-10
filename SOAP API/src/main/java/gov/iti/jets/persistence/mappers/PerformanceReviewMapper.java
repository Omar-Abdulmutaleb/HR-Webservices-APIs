package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.PerformanceReviewDTO;
import gov.iti.jets.persistence.entities.PerformanceReview;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PerformanceReviewMapper {
    PerformanceReviewMapper INSTANCE = Mappers.getMapper(PerformanceReviewMapper.class);

    @Mappings({
            @Mapping(source = "employeeId", target = "employee.employeeId"),
            @Mapping(source = "reviewerId", target = "reviewer.employeeId")
    })
    PerformanceReview toEntity(PerformanceReviewDTO performanceReviewDTO);

    @Mappings({
            @Mapping(source = "employee.employeeId", target = "employeeId"),
            @Mapping(source = "reviewer.employeeId", target = "reviewerId")
    })
    PerformanceReviewDTO toDto(PerformanceReview performanceReview);
}

