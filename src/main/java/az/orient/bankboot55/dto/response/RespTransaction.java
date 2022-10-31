package az.orient.bankboot55.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespTransaction {
    private Long transactionId;
    private RespAccount dtAccount;
    private String crAccount;
    private Double amount;
    private String currency;

}
