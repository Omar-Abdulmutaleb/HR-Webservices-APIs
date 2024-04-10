package gov.iti.jets.controllers;

import gov.iti.jets.persistence.DTOs.EmployeeDTO;
import gov.iti.jets.services.EmployeeService;
import gov.iti.jets.utils.responseBuilder.ResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("employee")
public class EmployeeController implements ControllerInt<EmployeeDTO> {

    @Context
    private UriInfo uriInfo;
    private final EmployeeService employeeService = new EmployeeService();


    /**
     * Creates a new employee.
     *
     * @param entity The employee to create.
     * @return A {@link Response} object representing the HTTP response.
     *         If the employee is successfully created, returns a {@link Response.Status#CREATED} response.
     *         If the employee is not created, returns a {@link Response.Status#BAD_REQUEST} response.
     */
    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(EmployeeDTO entity) {
        EmployeeDTO persistedEmployee = employeeService.createEmployee(entity);
        if (persistedEmployee != null && persistedEmployee.getEmployeeId() != null) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Integer.toString(persistedEmployee.getEmployeeId()));
            Link self = Link.fromUri(builder.build()).rel("self").build();
            return ResponseBuilder.CREATED(persistedEmployee, self);
        } else {
            return ResponseBuilder.BAD_REQUEST("Employee not created");
        }
    }

    /**
     * Retrieves all employees.
     *
     * @return A {@link Response} object representing the HTTP response.
     *         If there are no employees, returns a {@link Response.Status#NO_CONTENT} response.
     *         If employees are found, returns a {@link Response.Status#OK} response with the list of employees.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("1") Integer page, @QueryParam("size") @DefaultValue("5") Integer size) {
        List<EmployeeDTO> list = employeeService.getAllEmployees(page, size);
        if (list.isEmpty()) {
            return ResponseBuilder.OK();
        } else {
            return ResponseBuilder.SUCCESS(list);
        }
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return A {@link Response} object representing the HTTP response.
     *         If the ID is null, returns a {@link Response.Status#BAD_REQUEST} response.
     *         If the employee with the given ID is found, returns a {@link Response.Status#OK} response with the employee information.
     *         If no employee is found with the given ID, returns a {@link Response.Status#NOT_FOUND} response.
     */
    @Override
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Integer id) {
        if (id == null) {
            return ResponseBuilder.BAD_REQUEST("ID cannot be null");
        }
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        //if (employee != null) {
            return ResponseBuilder.SUCCESS(employee);
       // }
    }

    @GET
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesByJobTitle(@QueryParam("jobTitle") String jobTitle) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByJobTitle(jobTitle);
        if (employees.isEmpty()) {
            return ResponseBuilder.OK();
        } else {
            return ResponseBuilder.SUCCESS(employees);
        }
    }



    /**
     * Updates an employee.
     *
     * @param id The ID of the employee to update.
     * @param employeeDTO The updated employee information.
     * @return A {@link Response} object representing the HTTP response.
     *         If the employee is successfully updated, returns a {@link Response.Status#OK} response.
     *         If the employee ID is null, returns a {@link Response.Status#BAD_REQUEST} response.
     */

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, EmployeeDTO employeeDTO) {
        if (employeeDTO.getEmployeeId() == null) {
            employeeDTO.setEmployeeId(id);
        }
        employeeService.updateEmployee(employeeDTO);
        return ResponseBuilder.SUCCESS(employeeDTO);
    }


    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to delete.
     * @return A {@link Response} object representing the HTTP response.
     *         If the employee is successfully deleted, returns a {@link Response.Status#OK} response.
     *         If no employee is found with the given ID, returns a {@link Response.Status#NOT_FOUND} response.
     */
    @Override
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        try {
            employeeService.deleteEmployeeById(id);
            return ResponseBuilder.DELETED();
        } catch (Exception e) {
            return ResponseBuilder.NOT_FOUND();
        }
    }

}
