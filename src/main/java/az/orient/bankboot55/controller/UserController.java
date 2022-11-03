package az.orient.bankboot55.controller;

import az.orient.bankboot55.dto.request.ReqToken;
import az.orient.bankboot55.dto.request.ReqUser;
import az.orient.bankboot55.dto.response.RespStatus;
import az.orient.bankboot55.dto.response.RespUser;
import az.orient.bankboot55.dto.response.Response;
import az.orient.bankboot55.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public RespUser login(@RequestBody ReqUser reqUser) {
        return userService.login(reqUser);
    }

    @PostMapping("/logout")
    public Response logout(@RequestBody ReqToken reqToken) {
        return userService.logout(reqToken);
    }

}
