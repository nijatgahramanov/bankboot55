package az.orient.bankboot55.controller;


import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor //sadece final deyerler ucun allArgsConstructor vrzifesini yerine yetirir
public class CustomerController {

    private final CustomerService customerService;



    //gonderilen response un icinde response<T> ve hemin T nin listi client e gonderilecek
    @GetMapping("/GetCustomerList")
    public Response<List<RespCustomer>> getCustomerList() {
        return customerService.getCustomerList();
    }

}
