package az.orient.bankboot55.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespCustomer {
    //response olarag customerin hansi informasiyalari qayidacaqsa burada yaradilacaq

    @JsonProperty(value = "customerId") //variable olaraq id ile işleyib,front'a qayidan json'da customerİd gorunecek
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String dob;
    private String cif;
    private String pin;
    private String seria;
}
