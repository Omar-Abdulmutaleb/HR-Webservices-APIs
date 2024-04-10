package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.PerformanceReviewDTO;
import gov.iti.jets.services.PerformanceReviewService;
import gov.iti.jets.utils.responseBuilder.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("review")
public class PerformanceReviewController implements ControllerInt<PerformanceReviewDTO> {
    @Context
    private UriInfo uriInfo;
    private PerformanceReviewService performanceReviewService = new PerformanceReviewService();

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PerformanceReviewDTO performanceReviewDTO) {
        PerformanceReviewDTO persistedPerformanceReview = performanceReviewService.createPerformanceReview(performanceReviewDTO);
        if (persistedPerformanceReview != null && persistedPerformanceReview.getReviewId() != null) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(persistedPerformanceReview.getReviewId()));
            Link self = Link.fromUri(builder.build()).rel("self").build();
            return ResponseBuilder.CREATED(persistedPerformanceReview, self);
        } else {
            return ResponseBuilder.ERROR("Performance review not created");
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("size") @DefaultValue("5") Integer size) {
        List<PerformanceReviewDTO> list = performanceReviewService.getAllPerformanceReviews(page, size);
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
        PerformanceReviewDTO performanceReviewDTO = performanceReviewService.getPerformanceReviewById(id);
        if (performanceReviewDTO != null) {
            return ResponseBuilder.SUCCESS(performanceReviewDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }

    @Override
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, PerformanceReviewDTO performanceReviewDTO) {
        if (performanceReviewDTO.getReviewId() == null) {
            performanceReviewDTO.setReviewId(id);
        }
        performanceReviewService.updatePerformanceReview(performanceReviewDTO);
        return ResponseBuilder.SUCCESS(performanceReviewDTO);
    }

    @Override
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        performanceReviewService.deletePerformanceReviewById(id);
        return ResponseBuilder.DELETED();
    }
}