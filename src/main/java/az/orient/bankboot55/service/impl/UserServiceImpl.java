package az.orient.bankboot55.service.impl;

import az.orient.bankboot55.dto.request.ReqToken;
import az.orient.bankboot55.dto.request.ReqUser;
import az.orient.bankboot55.dto.response.RespStatus;
import az.orient.bankboot55.dto.response.RespToken;
import az.orient.bankboot55.dto.response.RespUser;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.entity.User;
import az.orient.bankboot55.enums.EnumAvailableStatus;
import az.orient.bankboot55.exception.BankException;
import az.orient.bankboot55.exception.ExceptionConstant;
import az.orient.bankboot55.repository.UserRepository;
import az.orient.bankboot55.service.UserService;
import az.orient.bankboot55.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utility utility;

    @Override
    public RespUser login(ReqUser reqUser) {
        RespUser response = new RespUser();
        User user = null;
        try {
            String username = reqUser.getUsername();
            String password = reqUser.getPassword();
            if (username == null || password == null) {
                throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data");
            }

            user = userRepository.findUserByUsernameAndPasswordAndActive(username, password, EnumAvailableStatus.ACTIVE.getValue());
            if (user == null) {
                throw new BankException(ExceptionConstant.INVALID_USER, "Invalid user");
            }
            if (user.getToken() != null) {
                throw new BankException(ExceptionConstant.USER_ALREADY_EXIST_IN_SESSION, "User already exist in this session");
            }

            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userRepository.save(user);

            response.setFullName(user.getFullName());
            response.setUsername(user.getUsername());

            RespToken respToken = new RespToken();
            respToken.setUserId(user.getId());
            respToken.setToken(user.getToken());

            response.setRespToken(respToken);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            if(user != null){
                RespToken respToken = new RespToken();
                respToken.setUserId(user.getId());
                respToken.setToken(user.getToken());
                response.setRespToken(respToken);
            }
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
            ex.printStackTrace();
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response logout(ReqToken reqToken) {
        Response response = new Response();
        try {
            User user = utility.checkToken(reqToken);
            user.setToken(null);
            userRepository.save(user);
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
}
