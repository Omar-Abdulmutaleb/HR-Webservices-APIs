package gov.iti.jets.persistence.mappers;

import gov.iti.jets.persistence.DTOs.TrainingSessionDTO;
import gov.iti.jets.persistence.entities.TrainingSession;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrainingSessionMapper {
    TrainingSessionMapper INSTANCE = Mappers.getMapper(TrainingSessionMapper.class);

    TrainingSession toEntity(TrainingSessionDTO trainingSessionDTO);

    TrainingSessionDTO toDto(TrainingSession trainingSession);
}
