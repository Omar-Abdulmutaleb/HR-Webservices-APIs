package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.TrainingSessionDTO;
import gov.iti.jets.persistence.entities.TrainingSession;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T18:42:16+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class TrainingSessionMapperImpl implements TrainingSessionMapper {

    @Override
    public TrainingSession toEntity(TrainingSessionDTO trainingSessionDTO) {
        if ( trainingSessionDTO == null ) {
            return null;
        }

        TrainingSession trainingSession = new TrainingSession();

        trainingSession.setSessionId( trainingSessionDTO.getSessionId() );
        trainingSession.setTrainingName( trainingSessionDTO.getTrainingName() );
        trainingSession.setStartDate( trainingSessionDTO.getStartDate() );
        trainingSession.setEndDate( trainingSessionDTO.getEndDate() );
        trainingSession.setLocation( trainingSessionDTO.getLocation() );

        return trainingSession;
    }

    @Override
    public TrainingSessionDTO toDto(TrainingSession trainingSession) {
        if ( trainingSession == null ) {
            return null;
        }

        TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();

        trainingSessionDTO.setSessionId( trainingSession.getSessionId() );
        trainingSessionDTO.setTrainingName( trainingSession.getTrainingName() );
        trainingSessionDTO.setStartDate( trainingSession.getStartDate() );
        trainingSessionDTO.setEndDate( trainingSession.getEndDate() );
        trainingSessionDTO.setLocation( trainingSession.getLocation() );

        return trainingSessionDTO;
    }
}
