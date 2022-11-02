package az.orient.bankboot55.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqTransaction {
    private Long dtAccountId;
    private String crAccount;
    private Double amount;
    private String currency;
}
