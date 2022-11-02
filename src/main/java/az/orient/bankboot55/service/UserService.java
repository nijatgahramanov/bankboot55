package az.orient.bankboot55.service;

import az.orient.bankboot55.dto.request.ReqUser;
import az.orient.bankboot55.dto.response.RespUser;

public interface UserService {

    RespUser login(ReqUser reqUser);
}
