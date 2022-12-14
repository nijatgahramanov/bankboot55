package az.orient.bankboot55.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespAccount {

    private Long accountId;
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private RespCustomer respCustomer;
    private RespStatus status;

}
