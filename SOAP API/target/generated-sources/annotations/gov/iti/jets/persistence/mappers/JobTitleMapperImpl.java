package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.JobTitleDTO;
import gov.iti.jets.persistence.entities.JobTitle;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T21:01:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class JobTitleMapperImpl implements JobTitleMapper {

    @Override
    public JobTitle toEntity(JobTitleDTO jobTitleDTO) {
        if ( jobTitleDTO == null ) {
            return null;
        }

        JobTitle jobTitle = new JobTitle();

        jobTitle.setJobTitleId( jobTitleDTO.getJobTitleId() );
        jobTitle.setTitle( jobTitleDTO.getTitle() );

        return jobTitle;
    }

    @Override
    public JobTitleDTO toDto(JobTitle jobTitle) {
        if ( jobTitle == null ) {
            return null;
        }

        JobTitleDTO jobTitleDTO = new JobTitleDTO();

        jobTitleDTO.setJobTitleId( jobTitle.getJobTitleId() );
        jobTitleDTO.setTitle( jobTitle.getTitle() );

        return jobTitleDTO;
    }
}
