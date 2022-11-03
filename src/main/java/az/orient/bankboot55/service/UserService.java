package az.orient.bankboot55.service;

import az.orient.bankboot55.dto.request.ReqToken;
import az.orient.bankboot55.dto.request.ReqUser;
import az.orient.bankboot55.dto.response.RespStatus;
import az.orient.bankboot55.dto.response.RespUser;
import az.orient.bankboot55.dto.response.Response;

public interface UserService {

    RespUser login(ReqUser reqUser);

    Response logout(ReqToken reqToken);
}
