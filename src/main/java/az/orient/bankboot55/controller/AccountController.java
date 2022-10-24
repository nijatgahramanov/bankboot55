package az.orient.bankboot55.controller;

import az.orient.bankboot55.dto.response.RespAccount;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/GetAccountList")
    public Response<List<RespAccount>> getAccountList() {
        return accountService.getAccountList();
    }

    @GetMapping("/GetAccountListByCustomerId/{custId}")
    public Response<List<RespAccount>> getAccountListByCustomerId(@PathVariable("custId") Long customerId) {
        return accountService.getAccountListByCustomerId(customerId);
    }

    @GetMapping("/GetAccountById/{accountId}")
    public Response<RespAccount> getAccountById(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountById(accountId);
    }

}
