package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.AttendanceRecordDTO;
import gov.iti.jets.services.AttendanceRecordService;
import gov.iti.jets.utils.responseBuilder.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("attendance")
public class AttendanceRecordController implements ControllerInt<AttendanceRecordDTO> {
    @Context
    private UriInfo uriInfo;
    private AttendanceRecordService attendanceRecordService = new AttendanceRecordService();

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AttendanceRecordDTO attendanceRecordDTO) {
        AttendanceRecordDTO persistedRecord = attendanceRecordService.createAttendanceRecord(attendanceRecordDTO);
        if (persistedRecord != null && persistedRecord.getAttendanceId() != null) {
            // Build the URI for the getOne method
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(persistedRecord.getAttendanceId()));
            Link self = Link.fromUri(builder.build()).rel("self").build();
            return ResponseBuilder.CREATED(persistedRecord, self);
        } else {
            return ResponseBuilder.ERROR("Attendance record not created");
        }
    }

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("size") @DefaultValue("5") Integer size) {
        List<AttendanceRecordDTO> list = attendanceRecordService.getAllAttendanceRecords(page, size);
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
        AttendanceRecordDTO attendanceRecordDTO = attendanceRecordService.getAttendanceRecordById(id);
        if (attendanceRecordDTO != null) {
            return ResponseBuilder.SUCCESS(attendanceRecordDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }

    @GET
    @Path("checkout/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkoutAttendance(@PathParam("id") Integer id) {
        AttendanceRecordDTO attendanceRecordDTO = attendanceRecordService.checkoutAttendance(id);
        System.out.println(attendanceRecordDTO);
        if (attendanceRecordDTO != null) {
            return ResponseBuilder.SUCCESS(attendanceRecordDTO);
        } else {
            return ResponseBuilder.NOT_FOUND();
        }
    }

    @GET
    @Path("employee/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttendanceRecordByEmployeeId(@PathParam("id") Integer id) {
        if (id == null || id == 0) {
            return ResponseBuilder.BAD_REQUEST("Employee ID cannot be null or zero, please provide a valid ID.");
        }
        List<AttendanceRecordDTO> attendanceRecordsDTO = null;
        try {
            attendanceRecordsDTO = attendanceRecordService.getAttendanceRecordByEmployeeId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee ID not found").build();
        }
        return ResponseBuilder.SUCCESS(attendanceRecordsDTO);
    }


    @GET
    @Path("day/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttendanceRecordsByDay(@PathParam("day") String day) {
        return ResponseBuilder.SUCCESS(attendanceRecordService.getAttendanceRecordsByDay(day));
    }


//    @PUT
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response update(@PathParam("id") Integer id, AttendanceRecordDTO attendanceRecordDTO) {
//        if (attendanceRecordDTO.getAttendanceId() == null) {
//            attendanceRecordDTO.setAttendanceId(id);
//        }
//        attendanceRecordService.updateAttendanceRecord(attendanceRecordDTO);
//       return ResponseBuilder.SUCCESS(attendanceRecordDTO);
//    }


    @Override
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        attendanceRecordService.deleteAttendanceRecordById(id);
        return ResponseBuilder.DELETED();
    }
}