package kz.dar.payment.api.rest;

import kz.dar.payment.api.model.AccountDTO;
import kz.dar.payment.api.model.AccountRequestModel;
import kz.dar.payment.api.repository.enttiy.AccountEntity;
import kz.dar.payment.api.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public AccountEntity createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);

    }

    @GetMapping
    public Iterable<AccountEntity> getAccount(@RequestParam String name,
                                              @RequestParam(required = false) String secondName,
                                              @RequestParam(required = false) String city) {
        return accountService.getAccount(new AccountRequestModel(name, secondName,city));
    }
}
