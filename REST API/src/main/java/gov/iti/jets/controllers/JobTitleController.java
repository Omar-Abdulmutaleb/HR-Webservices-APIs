package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.JobTitleDTO;
import gov.iti.jets.services.JobTitleService;
import gov.iti.jets.utils.Validator;
import gov.iti.jets.utils.responseBuilder.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("job")
public class JobTitleController implements ControllerInt<JobTitleDTO> , Validator {
    @Context
    private UriInfo uriInfo;
    private JobTitleService jobTitleService = new JobTitleService();

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(JobTitleDTO jobTitleDTO) {
        JobTitleDTO persistedJobTitle = jobTitleService.createJobTitle(jobTitleDTO);
        if (persistedJobTitle != null && persistedJobTitle.getJobTitleId() != null) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(persistedJobTitle.getJobTitleId()));
            Link self = Link.fromUri(builder.build()).rel("self").build();
            return ResponseBuilder.CREATED(persistedJobTitle, self);
        } else {
            return ResponseBuilder.ERROR("Job title is not created");
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("size") @DefaultValue("5") Integer size) {
        List<JobTitleDTO> list = jobTitleService.getAllJobTitles(page, size);
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
        JobTitleDTO jobTitleDTO = jobTitleService.getJobTitleById(id);
        if (jobTitleDTO != null) {
            return ResponseBuilder.SUCCESS(jobTitleDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }


    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobByName(@QueryParam("name") String name) {
        JobTitleDTO jobTitleDTO = jobTitleService.getJobTitleByName(name);
        if (jobTitleDTO != null) {
            return ResponseBuilder.SUCCESS(jobTitleDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }

    @Override
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, JobTitleDTO jobTitleDTO) {
        if (jobTitleDTO.getJobTitleId() == null) {
            jobTitleDTO.setJobTitleId(id);
        }
        jobTitleService.updateJobTitle(jobTitleDTO);
        return ResponseBuilder.SUCCESS(jobTitleDTO);
    }

    @Override
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        jobTitleService.deleteJobTitleById(id);
        return ResponseBuilder.DELETED();
    }
}