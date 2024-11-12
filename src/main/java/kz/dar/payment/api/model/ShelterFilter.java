package kz.dar.payment.api.model;

import lombok.Data;

@Data
public class ShelterFilter {

    private String name;
    private String type;
    private String location;
    private Integer minCapacity;
    private Integer maxCapacity;
    private Double minRating;
    private Double maxRating;
    private Double minAverageAdoptionTime;
    private Double maxAverageAdoptionTime;
    private Integer minDailyCost;
    private Integer maxDailyCost;
    private String noSpecificLocation;
}
