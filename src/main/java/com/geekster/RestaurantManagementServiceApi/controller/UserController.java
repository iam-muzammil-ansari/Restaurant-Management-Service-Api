package com.geekster.RestaurantManagementServiceApi.controller;

import com.geekster.RestaurantManagementServiceApi.model.DataToObject.SignInInput;
import com.geekster.RestaurantManagementServiceApi.model.DataToObject.SignUpOutput;
import com.geekster.RestaurantManagementServiceApi.model.User;
import com.geekster.RestaurantManagementServiceApi.service.AuthenticationService;
import com.geekster.RestaurantManagementServiceApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody @Valid SignInInput signInInput) {
        return userService.signIn(signInInput);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }

    @DeleteMapping("/signOut")
    public String sigOutPatient(String email, String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.sigOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }

    // Other API endpoints for CRUD operations can be added here
}
