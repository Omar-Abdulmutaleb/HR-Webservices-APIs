package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.persistence.DTOs.JobTitleDTO;
import gov.iti.jets.persistence.DTOs.TrainingSessionDTO;
import gov.iti.jets.services.TrainingSessionService;
import gov.iti.jets.utils.responseBuilder.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("session")
public class TrainingSessionController implements ControllerInt<TrainingSessionDTO> {
    @Context
    private UriInfo uriInfo;

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(TrainingSessionDTO trainingSessionDTO) {
        TrainingSessionDTO persistedTrainingSession = TrainingSessionService.createTrainingSession(trainingSessionDTO);
        if (persistedTrainingSession != null && persistedTrainingSession.getSessionId() != null) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(persistedTrainingSession.getSessionId()));
            Link self = Link.fromUri(builder.build()).rel("self").build();
            return ResponseBuilder.CREATED(persistedTrainingSession, self);
        } else {
            return ResponseBuilder.ERROR("Training session is not created");
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<TrainingSessionDTO> trainingSessionDTOs = TrainingSessionService.getAllTrainingSessions(page, size);
        return ResponseBuilder.SUCCESS(trainingSessionDTOs);
    }

    @Override
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Integer id) {
        TrainingSessionDTO trainingSessionDTO = TrainingSessionService.getTrainingSessionById(id);
        if (trainingSessionDTO == null) {
            return ResponseBuilder.NOT_FOUND();
        }
        return ResponseBuilder.SUCCESS(trainingSessionDTO);
    }

    @Override
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, TrainingSessionDTO trainingSessionDTO) {
        trainingSessionDTO.setSessionId(id);
        TrainingSessionDTO updatedTrainingSessionDTO = TrainingSessionService.updateTrainingSession(trainingSessionDTO);
        if (updatedTrainingSessionDTO == null) {
            return ResponseBuilder.NOT_FOUND();
        }
        return ResponseBuilder.SUCCESS(updatedTrainingSessionDTO);
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        TrainingSessionService.deleteTrainingSession(id);
        return ResponseBuilder.DELETED();
    }

    @GET
    @Path("employee/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByTrainingName(@PathParam("name") String name){
        List<EmployeeDTO> employeeDTO =  TrainingSessionService.getEmployeesByTrainingName(name);
        return ResponseBuilder.SUCCESS(employeeDTO);
    }
}