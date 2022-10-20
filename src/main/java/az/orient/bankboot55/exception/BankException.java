package az.orient.bankboot55.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BankException extends RuntimeException {

    private Integer code;

    public BankException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

}
