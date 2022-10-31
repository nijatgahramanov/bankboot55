package az.orient.bankboot55.dto.request;


import lombok.Data;



@Data
public class ReqAccount {
    private Long id;
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private Long customerId;

}
