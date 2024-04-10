package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.JobTitleDTO;
import gov.iti.jets.persistence.entities.JobTitle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobTitleMapper {
    JobTitleMapper INSTANCE = Mappers.getMapper(JobTitleMapper.class);

    JobTitle toEntity(JobTitleDTO jobTitleDTO);

    JobTitleDTO toDto(JobTitle jobTitle);
}

