package com.second.project.controller;

import com.second.project.entity.UserEntity;
import com.second.project.response.APIResponse;
import com.second.project.service.AuthenticationService;
import com.second.project.service.JwtService;
import com.second.project.service.ResponseService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.second.project.response.LoginResponse;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private ResponseService<UserEntity> responseService;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse<UserEntity>> register(@RequestBody UserEntity registerUser) {
        UserEntity registeredUser = authenticationService.signup(registerUser);
        return responseService.returnResponse("user loing seccussfully", 200, registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserEntity loginUser) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);

    }

}
