package az.orient.bankboot55.service;

import az.orient.bankboot55.dto.request.ReqTransaction;
import az.orient.bankboot55.dto.response.RespTransaction;
import az.orient.bankboot55.dto.response.Response;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionService {
    Response<List<RespTransaction>> getTransactionList();
    Response addTransaction(ReqTransaction reqTransaction);
}
