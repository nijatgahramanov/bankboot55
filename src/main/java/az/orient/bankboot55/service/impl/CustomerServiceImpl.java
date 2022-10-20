package az.orient.bankboot55.service.impl;

import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.RespStatus;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.entity.Customer;
import az.orient.bankboot55.enums.EnumAvailableStatus;
import az.orient.bankboot55.exception.BankException;
import az.orient.bankboot55.exception.ExceptionConstant;
import az.orient.bankboot55.repository.CustomerRepository;
import az.orient.bankboot55.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        List<RespCustomer> respCustomerList = new ArrayList<>();

        try {
            List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());

            if (customerList.isEmpty()) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }

            for (Customer customer : customerList) {
                RespCustomer respCustomer = convert(customer);
                respCustomerList.add(respCustomer);
            }
            response.setT(respCustomerList);
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

    private RespCustomer convert(Customer customer) {
        RespCustomer respCustomer = new RespCustomer();
        respCustomer.setId(customer.getId());
        respCustomer.setUsername(customer.getUsername());
        respCustomer.setName(customer.getName());
        respCustomer.setSurname(customer.getSurname());
        respCustomer.setDob(customer.getDob());
        respCustomer.setCif(customer.getCif());
        respCustomer.setSeria(customer.getSeria());
        respCustomer.setPin(customer.getPin());
        respCustomer.setPhone(customer.getPhone());

        return respCustomer;
    }

}
