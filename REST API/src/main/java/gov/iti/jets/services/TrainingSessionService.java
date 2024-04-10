package gov.iti.jets.services;

import gov.iti.jets.persistence.DAOs.implementations.TrainingSessionDAO;
import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.persistence.DTOs.TrainingSessionDTO;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.TrainingSession;
import gov.iti.jets.persistence.mappers.EmployeeMapper;
import gov.iti.jets.persistence.mappers.TrainingSessionMapper;
import gov.iti.jets.persistence.utils.EntityUpdateUtil;
import gov.iti.jets.utils.JPATransactionManager;
import gov.iti.jets.utils.exceptionBuilder.BusinessException;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrainingSessionService {

    public static TrainingSessionDTO createTrainingSession(TrainingSessionDTO trainingSessionDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO(em);
            TrainingSession trainingSession = TrainingSessionMapper.INSTANCE.toEntity(trainingSessionDTO);
            trainingSession.setStartDate(LocalDateTime.now());
            trainingSession.setEndDate(LocalDateTime.now().plusDays(10));
            trainingSessionDAO.create(trainingSession);
            return TrainingSessionMapper.INSTANCE.toDto(trainingSession);
        });
    }

    public static List<TrainingSessionDTO> getAllTrainingSessions(Integer page, Integer size) {
        return JPATransactionManager.doInTransaction(em -> {
            TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO(em);
            List<TrainingSession> trainingSessions = trainingSessionDAO.findAll(page, size);
            List<TrainingSessionDTO> trainingSessionDTOS = new ArrayList<>();
            for (TrainingSession trainingSession : trainingSessions) {
                trainingSessionDTOS.add(TrainingSessionMapper.INSTANCE.toDto(trainingSession));
            }
            return trainingSessionDTOS;
        });
    }


    public static TrainingSessionDTO getTrainingSessionById(Integer id) {
        return JPATransactionManager.doInTransaction(em -> {
            TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO(em);
            TrainingSession trainingSession = trainingSessionDAO.findById(id);
            if (trainingSession == null) {
                return null;
            }
            return TrainingSessionMapper.INSTANCE.toDto(trainingSession);
        });
    }


    public static List<EmployeeDTO> getEmployeesByTrainingName(String name){
        return JPATransactionManager.doInTransaction(em->{
            TrainingSessionDAO trainingRepo = new TrainingSessionDAO(em);
            TrainingSession trainingExists = trainingRepo.findTrainingByName(name);
            if (trainingExists == null) {
                throw new BusinessException(Response.Status.NOT_FOUND.getReasonPhrase(), Response.Status.NOT_FOUND.getStatusCode()
                        , "Training with name: " + name + " is not found");
            }

            List<Employee> employeesByTrainingName = trainingRepo.findEmployeesByTrainingName(name);

            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            for (Employee employee : employeesByTrainingName) {
                employeeDTOS.add(EmployeeMapper.INSTANCE.toDto(employee));
            }
            return employeeDTOS;
        });
    }

    public static TrainingSessionDTO updateTrainingSession(TrainingSessionDTO trainingSessionDTO) {
        return JPATransactionManager.doInTransaction(em -> {
            TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO(em);
            TrainingSession existingTrainingSession = trainingSessionDAO.findById(trainingSessionDTO.getSessionId());
            if (existingTrainingSession != null) {
                EntityUpdateUtil.copyNonNullProperties(trainingSessionDTO, existingTrainingSession);
                trainingSessionDAO.update(existingTrainingSession);
            }
            return TrainingSessionMapper.INSTANCE.toDto(existingTrainingSession);
        });
    }

    public static void deleteTrainingSession(Integer id) {
        JPATransactionManager.doInTransaction(em -> {
            TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO(em);
            TrainingSession trainingSession = trainingSessionDAO.findById(id);
            if (trainingSession != null) {
                trainingSessionDAO.delete(trainingSession);
            }
            return null;
        });
    }
}
