package gov.iti.jets.persistence.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jobtitle")
public class JobTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_title_id")
    private Integer jobTitleId;

    @Column(name = "title")
    private String title;

    // Constructors, getters, and setters
}
