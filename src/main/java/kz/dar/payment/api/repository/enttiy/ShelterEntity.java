package kz.dar.payment.api.repository.enttiy;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document("shelters")
@Getter
@Setter
public class ShelterEntity {

    @Id
    private String id;
    private String name;
    private String location;
    private Integer capacity;
    private String type;
    private Double rating;
    private boolean isGovernmentFunded;
    private Double averageAdoptionTime;
    private Integer dailyCost;
}
