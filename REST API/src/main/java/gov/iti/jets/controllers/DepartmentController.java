package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.DepartmentDTO;
import gov.iti.jets.services.DepartmentService;
import gov.iti.jets.utils.responseBuilder.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("department")
public class DepartmentController implements ControllerInt<DepartmentDTO>{
    @Context
    private UriInfo uriInfo;
    private DepartmentService departmentService = new DepartmentService();

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(DepartmentDTO departmentDTO) {
        DepartmentDTO persistedDepartment = departmentService.createDepartment(departmentDTO);
        if (persistedDepartment != null && persistedDepartment.getDepartmentId() != null) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(persistedDepartment.getDepartmentId()));
            Link self = Link.fromUri(builder.build()).rel("self").build();
            return ResponseBuilder.CREATED(persistedDepartment, self);
        } else {
            return ResponseBuilder.ERROR("Department not created");
        }
    }

    @Override
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Integer id) {
        if (id == null) {
            return ResponseBuilder.ERROR("ID cannot be null");
        }
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        if (departmentDTO != null) {
            return ResponseBuilder.SUCCESS(departmentDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("size") @DefaultValue("5") Integer size) {
        List<DepartmentDTO> list = departmentService.getAllDepartments(page, size);
        if (list.isEmpty()) {
            return ResponseBuilder.OK();
        } else {
            return ResponseBuilder.SUCCESS(list);
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, DepartmentDTO departmentDTO) {
        if (departmentDTO.getDepartmentId() == null) {
            departmentDTO.setDepartmentId(id);
        }
        departmentService.updateDepartment(departmentDTO);
        return ResponseBuilder.SUCCESS(departmentDTO);
    }

    @Override
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        departmentService.deleteDepartmentById(id);
        return ResponseBuilder.DELETED();
    }
}