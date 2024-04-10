package gov.iti.jets;

import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * AbstractTestClass provides common setup and utility methods for test classes.
 */
public abstract class AbstractTestClass {

    /**
     * Base URL for the REST API.
     */
    protected static final String BASE_URL = "http://localhost:9090/rest-api/webapi";

    /**
     * Client instance to perform HTTP requests.
     */
    protected Client client;

    /**
     * Request builder for HTTP requests.
     */
    protected Invocation.Builder request;

    /**
     * Sets up the client before each test method.
     */
    @BeforeEach
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    /**
     * Abstract method to be implemented by subclasses to provide the endpoint for the API being tested.
     *
     * @return the endpoint string
     */
    protected abstract String getEndpoint();


    /**
     * Test method to perform a POST request to create a new resource.
     *
     * @param dto the DTO to be created
     */
    public void testCreate(Object dto) {
        // Arrange
        Entity<Object> entity = Entity.entity(dto, MediaType.APPLICATION_JSON);
        request = client.target(BASE_URL + getEndpoint())
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.post(entity);

        // Assert
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }



    /**
     * Test method to perform a GET request with pagination.
     *
     * @param page the page number
     * @param size the size of each page
     */
    public void testGetAll(Integer page, Integer size) {
        // Arrange
        request = client.target(BASE_URL + getEndpoint())
                .queryParam("page", page)
                .queryParam("size", size)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test method to perform a GET request to retrieve a single resource by ID.
     *
     * @param id the ID of the resource
     */
    public void testGetOne(Integer id) {
        // Arrange
        request = client.target(BASE_URL + getEndpoint() + "/" + id)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test method to perform a PUT request to update a specific resource by id.
     *
     * @param id the id of the resource
     * @param dto the updated DTO
     */
    public void testUpdate(Integer id, Object dto) {
        // Arrange
        Entity<Object> entity = Entity.entity(dto, MediaType.APPLICATION_JSON);
        request = client.target(BASE_URL + getEndpoint() + "/" + id)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.put(entity);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


    /**
     * Test method to perform a DELETE request to delete a specific resource by id.
     *
     * @param id the id of the resource
     */
    public void testDelete(Integer id) {
        // Arrange
        request = client.target(BASE_URL + getEndpoint() + "/" + id)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.delete();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
