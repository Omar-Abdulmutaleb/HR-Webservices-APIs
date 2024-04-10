package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.persistence.DTOs.TrainingSessionDTO;
import gov.iti.jets.services.TrainingSessionService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public class TrainingSessionController {

    @WebMethod
    public TrainingSessionDTO createTrainingSession(TrainingSessionDTO trainingSessionDTO) {
        return TrainingSessionService.createTrainingSession(trainingSessionDTO);
    }

    @WebMethod
    public List<TrainingSessionDTO> getAllTrainingSessions(Integer page, Integer size) {
        return TrainingSessionService.getAllTrainingSessions(page, size);
    }

    @WebMethod
    public TrainingSessionDTO getTrainingSessionById(Integer id) {
        return TrainingSessionService.getTrainingSessionById(id);
    }

    @WebMethod
    public TrainingSessionDTO updateTrainingSession(Integer id, TrainingSessionDTO trainingSessionDTO) {
        trainingSessionDTO.setSessionId(id);
        return TrainingSessionService.updateTrainingSession(trainingSessionDTO);
    }

    @WebMethod
    public void deleteTrainingSession(Integer id) {
        TrainingSessionService.deleteTrainingSession(id);
    }

    @WebMethod
    public List<EmployeeDTO> getEmployeesByTrainingName(String name) {
        return TrainingSessionService.getEmployeesByTrainingName(name);
    }
}
