package kz.dar.payment.api.service;

import com.querydsl.core.BooleanBuilder;
import kz.dar.payment.api.model.AccountDTO;
import kz.dar.payment.api.model.AccountRequestModel;
import kz.dar.payment.api.repository.AccountRepository;
import kz.dar.payment.api.repository.enttiy.AccountEntity;
import kz.dar.payment.api.repository.enttiy.QAccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AccountService {


    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public AccountEntity createAccount(AccountDTO accountDTO) {

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId(UUID.randomUUID().toString());
        accountEntity.setNumber("KZ-" + UUID.randomUUID());
        accountEntity.setActive(true);
        accountEntity.setCity(accountDTO.getCity());
        accountEntity.setBalance(accountDTO.getBalance());
        accountEntity.setOwnerName(accountDTO.getOwnerName());
        accountEntity.setCurrency(accountDTO.getCurrency());
        accountEntity.setOwnerSecondName(accountDTO.getOwnerSecondName());

        return accountRepository.save(accountEntity);

    }


    public Iterable<AccountEntity>  getAccount(AccountRequestModel accountRequestModel) {
//        return accountRepository.findByOwnerNameAndOwnerSecondName(accountRequestModel.getName(), accountRequestModel.getSecondName());

        QAccountEntity qAccount = QAccountEntity.accountEntity;
        BooleanBuilder filterPredicate = new BooleanBuilder();

        filterPredicate.and(qAccount.active.isTrue());
        filterPredicate.and(qAccount.currency.contains("KZT"));
        filterPredicate.or(qAccount.currency.contains("USD"));

        if (accountRequestModel.getSecondName() != null) {
            filterPredicate.and(
                    qAccount.ownerName.like("%" + accountRequestModel.getName() + "%")
                            .and(qAccount.ownerSecondName.like("%" + accountRequestModel.getSecondName() + "%")));
        } else {
            filterPredicate.and(qAccount.ownerName.like("%" + accountRequestModel.getName() + "%"));
        }

        if (accountRequestModel.getCity() != null) {
            filterPredicate.and(qAccount.city.eq(accountRequestModel.getCity()));
        }


        return accountRepository.findAll(filterPredicate);
    }
}
