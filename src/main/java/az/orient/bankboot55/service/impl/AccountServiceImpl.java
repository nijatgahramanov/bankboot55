package az.orient.bankboot55.service.impl;

import az.orient.bankboot55.dto.response.RespAccount;
import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.RespStatus;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.entity.Account;
import az.orient.bankboot55.enums.EnumAvailableStatus;
import az.orient.bankboot55.exception.BankException;
import az.orient.bankboot55.exception.ExceptionConstant;
import az.orient.bankboot55.repository.AccountRepository;
import az.orient.bankboot55.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Response<List<RespAccount>> getAccountList() {

        Response response = new Response();
        List<RespAccount> respAccountList = new ArrayList<>();
        try {
            List<Account> accountList = accountRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (accountList.isEmpty()) {
                throw new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND, "Account not found");
            }
            for (Account account : accountList) {
                RespAccount respAccount = convert(account);
                respAccountList.add(respAccount);
            }
            response.setT(respAccountList);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exceoption"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<List<RespAccount>> getAccountListByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Response<RespAccount> getAccountById(Long accountId) {
        return null;
    }

    private RespAccount convert(Account account) {
        RespAccount respAccount = new RespAccount();
        respAccount.setAccountId(account.getId());
        respAccount.setAccountNo(account.getAccountNo());
        respAccount.setIban(account.getIban());
        respAccount.setCurrency(account.getCurrency());
        RespCustomer respCustomer = new RespCustomer();
        respCustomer.setId(account.getCustomer().getId());
        respCustomer.setName(account.getCustomer().getName());
        respCustomer.setSurname(account.getCustomer().getSurname());
        respAccount.setRespCustomer(respCustomer);

        return respAccount;
    }
}
