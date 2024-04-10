package gov.iti.jets;

import gov.iti.jets.persistence.DTOs.TrainingSessionDTO;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingSessionControllerTestClass extends AbstractTestClass {

    @Override
    protected String getEndpoint() {
        return "/session";
    }

    @Test
    public void testCreateTrainingSession() {
        TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();
        trainingSessionDTO.setEmployeeId(1);
        trainingSessionDTO.setTrainingName("C-Sharp");
        trainingSessionDTO.setLocation("Peking");
        testCreate(trainingSessionDTO);
    }

    @Test
    public void testGetAllTrainingSessions() {
        testGetAll(1, 5);
    }

    @Test
    public void testGetTrainingSessionById() {
        testGetOne(1); // replace 1 with the id of the training session you want to test
    }

    @Test
    public void testUpdateTrainingSession() {
        TrainingSessionDTO trainingSessionDTO = new TrainingSessionDTO();
        trainingSessionDTO.setTrainingName("Python");
        trainingSessionDTO.setLocation("Moscow");
        testUpdate(1, trainingSessionDTO);
    }

    @Test
    public void testDeleteTrainingSession() {
        testDelete(1);
    }

    @Test
    public void testGetEmployeeByTrainingName() {
        // Arrange
        String trainingNameToSearch = "Python";
        request = client.target(BASE_URL + getEndpoint() + "/employee/" + trainingNameToSearch)
                .request(MediaType.APPLICATION_JSON);

        // Act
        Response response = request.get();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


}