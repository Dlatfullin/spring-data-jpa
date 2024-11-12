package kz.dar.payment.api.service;

import com.querydsl.core.BooleanBuilder;
import kz.dar.payment.api.model.ShelterFilter;
import kz.dar.payment.api.model.ShelterSaveRequest;
import kz.dar.payment.api.repository.ShelterRepository;
import kz.dar.payment.api.repository.enttiy.QShelterEntity;
import kz.dar.payment.api.repository.enttiy.ShelterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShelterService {

    private final ShelterRepository shelterRepository;

    public ShelterEntity createShelter(ShelterSaveRequest shelterSaveRequest) {
        ShelterEntity shelterEntity = new ShelterEntity();

        shelterEntity.setId(UUID.randomUUID().toString());
        shelterEntity.setName(shelterSaveRequest.name());
        shelterEntity.setCapacity(shelterSaveRequest.capacity());
        shelterEntity.setRating(shelterSaveRequest.rating());
        shelterEntity.setAverageAdoptionTime(shelterSaveRequest.averageAdoptionTime());
        shelterEntity.setDailyCost(shelterSaveRequest.dailyCost());
        shelterEntity.setGovernmentFunded(shelterSaveRequest.isGovernmentFunded());
        shelterEntity.setLocation(shelterSaveRequest.location());
        shelterEntity.setType(shelterSaveRequest.type());

        return shelterRepository.save(shelterEntity);
    }

    public Iterable<ShelterEntity> getShelter(ShelterFilter shelterFilter, Pageable pageable) {
        QShelterEntity qShelter = QShelterEntity.shelterEntity;
        BooleanBuilder filter = new BooleanBuilder();

        if(shelterFilter.getName() != null) {
            filter.and(qShelter.name.like("%" + shelterFilter.getName() + "%"));
        }

        if(shelterFilter.getType() != null) {
            filter.and(qShelter.type.eq(shelterFilter.getType()));
        }
        if(shelterFilter.getLocation() != null) {
            filter.and(qShelter.location.like("%" + shelterFilter.getLocation() + "%"));
        }

        if(shelterFilter.getMinCapacity() != null) {
            filter.and(qShelter.capacity.goe(shelterFilter.getMinCapacity()));
        }
        if(shelterFilter.getMaxCapacity() != null) {
            filter.and(qShelter.capacity.loe(shelterFilter.getMaxCapacity()));
        }

        if(shelterFilter.getMinRating() != null) {
            filter.and(qShelter.rating.goe(shelterFilter.getMinRating()));
        }
        if(shelterFilter.getMaxRating() != null) {
            filter.and(qShelter.rating.loe(shelterFilter.getMaxRating()));
        }

        if(shelterFilter.getMinAverageAdoptionTime() != null) {
            filter.and(qShelter.averageAdoptionTime.goe(shelterFilter.getMinAverageAdoptionTime()));
        }
        if(shelterFilter.getMaxAverageAdoptionTime() != null) {
            filter.and(qShelter.averageAdoptionTime.loe(shelterFilter.getMaxAverageAdoptionTime()));
        }

        if(shelterFilter.getMinDailyCost() != null) {
            filter.and((qShelter.dailyCost.goe(shelterFilter.getMinDailyCost())));
        }
        if(shelterFilter.getMaxDailyCost() != null) {
            filter.and((qShelter.dailyCost.loe(shelterFilter.getMaxDailyCost())));
        }

        if (shelterFilter.getType() != null && shelterFilter.getLocation() != null) {
            filter.or(qShelter.type.eq(shelterFilter.getType()).and(qShelter.location.like("%" + shelterFilter.getLocation() + "%")));
        }

        if (shelterFilter.getNoSpecificLocation() != null) {
            filter.and(qShelter.location.ne(shelterFilter.getNoSpecificLocation()));
        }

        Page<ShelterEntity> page = shelterRepository.findAll(filter, pageable);
        return page.getContent();
    }
}
