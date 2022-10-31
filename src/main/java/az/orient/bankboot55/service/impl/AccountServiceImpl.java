package az.orient.bankboot55.service.impl;

import az.orient.bankboot55.dto.request.ReqAccount;
import az.orient.bankboot55.dto.response.RespAccount;
import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.RespStatus;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.entity.Account;
import az.orient.bankboot55.entity.Customer;
import az.orient.bankboot55.enums.EnumAvailableStatus;
import az.orient.bankboot55.exception.BankException;
import az.orient.bankboot55.exception.ExceptionConstant;
import az.orient.bankboot55.repository.AccountRepository;
import az.orient.bankboot55.repository.CustomerRepository;
import az.orient.bankboot55.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

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
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response<List<RespAccount>> getAccountListByCustomerId(Long customerId) {
        Response<List<RespAccount>> response = new Response<>();
        List<RespAccount> respAccountList = new ArrayList<>();
        try {
            if (customerId == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Customer customer = customerRepository.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            List<Account> accountList = accountRepository.findAllByCustomerAndActive(customer, EnumAvailableStatus.ACTIVE.getValue());

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
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return response;

    }

    @Override
    public Response<RespAccount> getAccountById(Long accountId) {
        Response<RespAccount> response = new Response<>();
        try {
            if (accountId == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data");
            }

            Account account = accountRepository.findAccountByIdAndActive(accountId, EnumAvailableStatus.ACTIVE.getValue());

            if (account == null) {
                throw new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND, "Account not found");
            }

            RespAccount respAccount = convert(account);


            response.setT(respAccount);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response addAccount(ReqAccount reqAccount) {
        Response response = new Response<>();

        try {
            String accountName = reqAccount.getName();
            Long customerId = reqAccount.getCustomerId();

            if (accountName == null || customerId == null) {
               throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA,"Invalid request data!");
            }

            Customer customer = customerRepository.findByIdAndActive(customerId,EnumAvailableStatus.ACTIVE.getValue());
            if(customer==null){
              throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND,"Customer not found");
            }

            Account account = new Account();
            account.setAccountNo(reqAccount.getAccountNo());
            account.setName(accountName);
            account.setCurrency(reqAccount.getCurrency());
            account.setIban(reqAccount.getIban());
            account.setCustomer(customer);
            accountRepository.save(account);

            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data!"));
            ex.printStackTrace();
        }


        return response;
    }

    private RespAccount convert(Account account) {
        RespAccount respAccount = new RespAccount();
        respAccount.setName(account.getName());
        respAccount.setAccountId(account.getId());
        respAccount.setAccountNo(account.getAccountNo());
        respAccount.setIban(account.getIban());
        respAccount.setCurrency(account.getCurrency());

        if (account.getCustomer() == null) {
            respAccount.setStatus(new RespStatus(ExceptionConstant.CUSTOMER_NOT_FOUND_FOR_THIS_ACCOUNT, "Customer not found for this account"));
            return respAccount;
        }
        RespCustomer respCustomer = new RespCustomer();
        respCustomer.setId(account.getCustomer().getId());
        respCustomer.setName(account.getCustomer().getName());
        respCustomer.setSurname(account.getCustomer().getSurname());
        respCustomer.setCif(account.getCustomer().getCif());
        respAccount.setRespCustomer(respCustomer);

        return respAccount;
    }


}
