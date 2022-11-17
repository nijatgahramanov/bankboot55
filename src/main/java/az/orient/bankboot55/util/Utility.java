package az.orient.bankboot55.util;

import az.orient.bankboot55.dto.request.ReqToken;
import az.orient.bankboot55.entity.User;
import az.orient.bankboot55.enums.EnumAvailableStatus;
import az.orient.bankboot55.exception.BankException;
import az.orient.bankboot55.exception.ExceptionConstant;
import az.orient.bankboot55.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Utility {

    private final UserRepository userRepository;

    public User checkToken(ReqToken reqToken) {
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();

        if (userId == null || token == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data");
        }
        User user = userRepository.findUserByIdAndTokenAndActive(userId, token, EnumAvailableStatus.ACTIVE.getValue());

        if (user == null) {
            throw new BankException(ExceptionConstant.INVALID_TOKEN, "Invalid token");
        }
        return user;
    }

}
