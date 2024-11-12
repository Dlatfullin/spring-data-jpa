package kz.dar.payment.api.model;

public record ShelterSaveRequest(String name,
                                 String location,
                                 Integer capacity,
                                 String type,
                                 Double rating,
                                 boolean isGovernmentFunded,
                                 Double averageAdoptionTime,
                                 Integer dailyCost) {
}
