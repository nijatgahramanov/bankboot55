package az.orient.bankboot55.controller;


import az.orient.bankboot55.dto.request.ReqCustomer;
import az.orient.bankboot55.dto.request.ReqToken;
import az.orient.bankboot55.dto.response.RespCustomer;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor //sadece final deyerler ucun allArgsConstructor vrzifesini yerine yetirir
public class CustomerController {

    private final CustomerService customerService;

    //gonderilen response un icinde response<T> ve hemin T nin listi client e gonderilecek
    @PostMapping(value = "/GetCustomerList",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Response<List<RespCustomer>> getCustomerList(@RequestBody ReqToken reqToken) {
        return customerService.getCustomerList(reqToken);
    }

    @GetMapping("/GetCustomerById/{custId}")
    public Response<RespCustomer> getCustomerById(@PathVariable("custId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/AddCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.addCustomer(reqCustomer);
    }

    @PutMapping("/UpdateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PutMapping("/DeleteCustomer")
    public Response deleteCustomer(@RequestParam("custId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }


}
