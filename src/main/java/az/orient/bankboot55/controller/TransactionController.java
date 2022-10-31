package az.orient.bankboot55.controller;


import az.orient.bankboot55.dto.response.RespTransaction;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.entity.Transaction;
import az.orient.bankboot55.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/GetTransactionList")
    public Response<List<RespTransaction>> getTransactionList(){
        return transactionService.getTransactionList();
    }


}
