package az.orient.bankboot55.service;

import az.orient.bankboot55.dto.response.RespAccount;
import az.orient.bankboot55.dto.response.Response;

import java.util.List;

public interface AccountService {
    Response<List<RespAccount>> getAccountList();

    Response<List<RespAccount>> getAccountListByCustomerId(Long customerId);

    Response<RespAccount> getAccountById(Long accountId);
}
