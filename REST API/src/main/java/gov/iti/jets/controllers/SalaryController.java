package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.SalaryDTO;
import gov.iti.jets.persistence.DTOs.TrainingSessionDTO;
import gov.iti.jets.services.SalaryService;
import gov.iti.jets.services.TrainingSessionService;
import gov.iti.jets.utils.Validator;
import gov.iti.jets.utils.responseBuilder.ResponseBuilder;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("salary")
public class SalaryController implements ControllerInt<SalaryDTO> , Validator {
    @Context
    private UriInfo uriInfo;
    private SalaryService salaryService = new SalaryService();

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(SalaryDTO salaryDTO) {
        SalaryDTO persistedSalary = salaryService.createSalary(salaryDTO);
        if (persistedSalary != null && persistedSalary.getSalaryId() != null) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(persistedSalary.getSalaryId()));
            Link self = Link.fromUri(builder.build()).rel("self").build();
            return ResponseBuilder.CREATED(persistedSalary, self);
        } else {
            return ResponseBuilder.ERROR("Salary not created");
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("size") @DefaultValue("5") Integer size) {
        List<SalaryDTO> list = salaryService.getAllSalaries(page, size);
        if (list.isEmpty()) {
            return ResponseBuilder.OK();
        } else {
            return ResponseBuilder.SUCCESS(list);
        }
    }

    @Override
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Integer id) {
        SalaryDTO salaryDTO = salaryService.getSalaryById(id);
        if (salaryDTO != null) {
            return ResponseBuilder.SUCCESS(salaryDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }

    @GET
    @Path("employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalaryByEmployeeId(@PathParam("id") Integer id) {
        SalaryDTO salaryDTO = salaryService.getSalaryByEmployeeId(id);
        if (salaryDTO != null) {
            return ResponseBuilder.SUCCESS(salaryDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }

    @PUT
    @Path("employee/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateByEmployeeId(@PathParam("id") Integer id, SalaryDTO salaryDTO) {
        validateId(id);
        SalaryDTO updatedSalaryDTO = salaryService.updateSalaryByEmployeeId(id, salaryDTO);
        if (updatedSalaryDTO == null) {
            return ResponseBuilder.NOT_FOUND();
        }
        return ResponseBuilder.SUCCESS(updatedSalaryDTO);
    }

    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        salaryService.deleteSalaryById(id);
        return ResponseBuilder.DELETED();
    }
}