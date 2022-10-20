package az.orient.bankboot55.service;

import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList(); //customerin mentiqi burada yazilacaq

}
