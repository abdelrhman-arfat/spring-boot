package com.second.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.second.project.entity.UserEntity;
import com.second.project.response.APIResponse;
import com.second.project.service.ResponseService;
import com.second.project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ResponseService<UserEntity> responseService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<UserEntity>> loginUser(@Valid @RequestBody UserEntity user) {
        try {
            UserEntity registeredUser = userService.getUserByEmail(user.getEmail());
            if (registeredUser == null) {
                return responseService.returnResponse("user not found", 404);
            }
            boolean isPasswordCorrect = userService.checkIfPasswordIsCorrect(user.getPassword(), registeredUser);
            if (!isPasswordCorrect) {
                return responseService.returnResponse("password is incorrect", 401);
            }
            return responseService.returnResponse("user log in successfully", 200, registeredUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return responseService.returnResponseError(e.getMessage());
        }
    }

}
