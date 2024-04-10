package gov.iti.jets.persistence.DTOs;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonbPropertyOrder({"jobTitleId", "title"})
@Data
public class JobTitleDTO implements Serializable {
    private Integer jobTitleId;
    private String title;
}
