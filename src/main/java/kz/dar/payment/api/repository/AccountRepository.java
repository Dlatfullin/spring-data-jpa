package kz.dar.payment.api.repository;

import kz.dar.payment.api.repository.enttiy.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends
        MongoRepository<AccountEntity, String>, QuerydslPredicateExecutor<AccountEntity> {

    List<AccountEntity> findByOwnerNameAndOwnerSecondName(String ownerName, String ownerSecondName);
}
