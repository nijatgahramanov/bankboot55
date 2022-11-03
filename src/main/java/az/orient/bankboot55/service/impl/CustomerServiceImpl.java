package az.orient.bankboot55.service.impl;

import az.orient.bankboot55.dto.request.ReqCustomer;
import az.orient.bankboot55.dto.request.ReqToken;
import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.RespStatus;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.entity.Customer;
import az.orient.bankboot55.enums.EnumAvailableStatus;
import az.orient.bankboot55.exception.BankException;
import az.orient.bankboot55.exception.ExceptionConstant;
import az.orient.bankboot55.repository.CustomerRepository;
import az.orient.bankboot55.service.CustomerService;
import az.orient.bankboot55.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private final Utility utility;

    @Override
    public Response<List<RespCustomer>> getCustomerList(ReqToken reqToken) {
        Response<List<RespCustomer>> response = new Response<>();
        List<RespCustomer> respCustomerList = new ArrayList<>();

        try {
            utility.checkToken(reqToken); //token controlleri edilecek
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
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception!"));
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response<RespCustomer> getCustomerById(Long customerId) {
        Response<RespCustomer> response = new Response<>();

        try {
            if (customerId == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data!");
            }
            Customer customer = customerRepository.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            RespCustomer respCustomer = convert(customer);
            response.setT(respCustomer);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found!"));
        }
        return response;
    }

    @Override
    public Response addCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();

        try {
            utility.checkToken(reqCustomer.getToken());
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (name == null || surname == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data!");
            }

            Customer customer = new Customer();
            customer.setName(name);
            customer.setSurname(surname);
            customer.setDob(reqCustomer.getDob());
            customer.setCif(reqCustomer.getCif());
            customer.setPhone(reqCustomer.getPhone());
            customer.setSeria(reqCustomer.getSeria());
            customer.setEmail(reqCustomer.getEmail());
            customer.setPassword(reqCustomer.getPassword());
            customer.setUsername(reqCustomer.getUsername());

            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());


        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found!"));
        }

        return response;
    }

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();

        try {
            Long customerId = reqCustomer.getId();
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (customerId == null
                    || name == null ||
                    surname == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data!");
            }

            Customer customer = customerRepository.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found!");
            }
            customer.setName(name);
            customer.setSurname(surname);
            customer.setDob(reqCustomer.getDob());
            customer.setCif(reqCustomer.getCif());
            customer.setPhone(reqCustomer.getPhone());
            customer.setSeria(reqCustomer.getSeria());
            customer.setEmail(reqCustomer.getEmail());
            customer.setPassword(reqCustomer.getPassword());
            customer.setUsername(reqCustomer.getUsername());

            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found!"));
        }
        return response;
    }

    @Override
    public Response deleteCustomer(Long customerId) {
        Response response = new Response();

        try {
            if (customerId == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data!");
            }
            Customer customer = customerRepository.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            customer.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            customerRepository.save(customer);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception!"));
        }

        return response;
    }


    private RespCustomer convert(Customer customer) {
        RespCustomer respCustomer = new RespCustomer();
        if (customer == null) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, "Customer not found!");
        }
        respCustomer.setId(customer.getId());
        respCustomer.setUsername(customer.getUsername());
        respCustomer.setName(customer.getName());
        respCustomer.setSurname(customer.getSurname());
        if (customer.getDob() != null)
            respCustomer.setDob(df.format(customer.getDob()));
        respCustomer.setCif(customer.getCif());
        respCustomer.setSeria(customer.getSeria());
        respCustomer.setPin(customer.getPin());
        respCustomer.setPhone(customer.getPhone());

        return respCustomer;
    }

}
