package az.orient.bankboot55.dto.response;

import lombok.Data;

@Data
public class RespAccount {

    private Long accountId;
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private RespCustomer respCustomer;

}
