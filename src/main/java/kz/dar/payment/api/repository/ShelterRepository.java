package kz.dar.payment.api.repository;

import com.querydsl.core.types.Predicate;
import kz.dar.payment.api.repository.enttiy.ShelterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends MongoRepository<ShelterEntity, String>,
        QuerydslPredicateExecutor<ShelterEntity> {
    Page<ShelterEntity> findAll(Predicate predicate, Pageable pageable);
}
