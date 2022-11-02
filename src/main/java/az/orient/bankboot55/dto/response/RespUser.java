package az.orient.bankboot55.dto.response;

import lombok.Data;

@Data
public class RespUser {

    private String username;
    private String fullName;
    private RespToken respToken;
    private RespStatus status;



}
