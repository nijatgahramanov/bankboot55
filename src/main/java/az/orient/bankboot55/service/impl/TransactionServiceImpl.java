package az.orient.bankboot55.service.impl;

import az.orient.bankboot55.dto.response.*;
import az.orient.bankboot55.entity.Account;
import az.orient.bankboot55.entity.Customer;
import az.orient.bankboot55.entity.Transaction;
import az.orient.bankboot55.enums.EnumAvailableStatus;
import az.orient.bankboot55.exception.BankException;
import az.orient.bankboot55.exception.ExceptionConstant;
import az.orient.bankboot55.repository.TransactionRepository;
import az.orient.bankboot55.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Response<List<RespTransaction>> getTransactionList() {
        Response<List<RespTransaction>> response = new Response<>();

        List<RespTransaction> transactionList = new ArrayList<>();
        try {
            List<Transaction> transactList = transactionRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (transactList.isEmpty()) {
                throw new BankException(ExceptionConstant.TRANSACTION_NOT_FOUND, "Transaction not found!");
            }

            for (Transaction transaction : transactList) {
                RespTransaction respTransaction = convert(transaction);
                transactionList.add(respTransaction);
            }
            response.setT(transactionList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }

        return response;
    }

    private RespTransaction convert(Transaction transaction) {
        Account account = transaction.getDtAccount();
        if (account == null) {
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND, "Debit account not found!");
        }

        RespCustomer respCustomer = RespCustomer.builder()
                .cif(account.getCustomer().getCif())
                .dob(df.format(account.getCustomer().getDob()))
                .id(account.getCustomer().getId())
                .email(account.getCustomer().getEmail())
                .phone(account.getCustomer().getPhone())
                .pin(account.getCustomer().getPin())
                .seria(account.getCustomer().getSeria())
                .name(account.getCustomer().getName())
                .surname(account.getCustomer().getSurname())
                .username(account.getCustomer().getUsername())
                .build();

        RespAccount respAccount = RespAccount.builder()
                .name(account.getName())
                .accountId(account.getId())
                .iban(account.getIban())
                .accountNo(account.getAccountNo())
                .currency(account.getCurrency())
                .respCustomer(respCustomer)
                .build();

        return RespTransaction.builder()
                .amount((double)transaction.getAmount()/100)
                .crAccount(transaction.getCrAccount())
                .currency(transaction.getCurrency())
                .transactionId(transaction.getId())
                .dtAccount(respAccount)
                .build();


    }
}
