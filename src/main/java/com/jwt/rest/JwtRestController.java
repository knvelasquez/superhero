package com.jwt.rest;


import com.jwt.api.JwtApi;
import com.user.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class JwtRestController {
    private final JwtApi jwtApi;
    private final UserApi userApi;

    @Autowired
    public JwtRestController(JwtApi jwtApi, UserApi userApi) {
        this.jwtApi = jwtApi;
        this.userApi = userApi;
    }

    @RequestMapping(value = "jwt", method = RequestMethod.POST)
    public String create(@RequestBody JwtRequestModel jwtModel) {
        if (jwtModel.getIdUser() == 0) {
            return null;
        }
        final int idUser = jwtModel.getIdUser();
        final List<String> privileges = userApi.getAllPrivileges(idUser);
        return jwtApi.create(idUser, privileges);
    }
}
