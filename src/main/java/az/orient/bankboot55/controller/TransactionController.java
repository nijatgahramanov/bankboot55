package az.orient.bankboot55.controller;


import az.orient.bankboot55.dto.request.ReqTransaction;
import az.orient.bankboot55.dto.response.RespTransaction;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.entity.Transaction;
import az.orient.bankboot55.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/AddTransaction")
    public Response addTransaction(@RequestBody ReqTransaction reqTransaction){
        return transactionService.addTransaction(reqTransaction);
    }

}
