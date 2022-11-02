package az.orient.bankboot55.service;

import az.orient.bankboot55.dto.request.ReqCustomer;
import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList(); //customerin mentiqi burada yazilacaq

    Response<RespCustomer> getCustomerById(Long customerId);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(Long customerId);
}
