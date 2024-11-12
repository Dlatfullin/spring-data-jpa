package kz.dar.payment.api.rest;

import kz.dar.payment.api.model.ShelterFilter;
import kz.dar.payment.api.model.ShelterSaveRequest;
import kz.dar.payment.api.repository.enttiy.ShelterEntity;
import kz.dar.payment.api.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelter")
@RequiredArgsConstructor
public class ShelterController {

    private final ShelterService shelterService;

    @PostMapping
    public ResponseEntity<ShelterEntity> createShelter(@RequestBody ShelterSaveRequest saveRequest) {
        ShelterEntity shelter = shelterService.createShelter(saveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(shelter);
    }

    @GetMapping
    public Iterable<ShelterEntity> getShelters(ShelterFilter shelterFilter, Pageable pageable) {
        return shelterService.getShelter(shelterFilter, pageable);
    }
}
