package az.orient.bankboot55.controller;

import az.orient.bankboot55.dto.request.ReqAccount;
import az.orient.bankboot55.dto.request.ReqCustomer;
import az.orient.bankboot55.dto.response.RespAccount;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/AddAccount")
    public Response addAccount(@RequestBody ReqAccount reqAccount) {
        return accountService.addAccount(reqAccount);
    }


}
