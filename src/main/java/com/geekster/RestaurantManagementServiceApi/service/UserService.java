package com.geekster.RestaurantManagementServiceApi.service;

import com.geekster.RestaurantManagementServiceApi.model.AuthenticationToken;
import com.geekster.RestaurantManagementServiceApi.model.DataToObject.SignInInput;
import com.geekster.RestaurantManagementServiceApi.model.DataToObject.SignUpOutput;
import com.geekster.RestaurantManagementServiceApi.model.User;
import com.geekster.RestaurantManagementServiceApi.model.enums.UserRole;
import com.geekster.RestaurantManagementServiceApi.repository.AuthRepo;
import com.geekster.RestaurantManagementServiceApi.repository.UserRepo;
import com.geekster.RestaurantManagementServiceApi.service.utility.emailUtility.EmailHandler;
import com.geekster.RestaurantManagementServiceApi.service.utility.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthRepo authRepo;

    public String sigOutUser(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        authRepo.delete(authRepo.findFirstByUser(user));
        return "User Signed out Successfully";
    }


    public SignUpOutput signUp(User user) {
        SignUpOutput signUpOutput = new SignUpOutput();
        String email = user.getUserEmail();
        if (email == null || email.isEmpty()) {
            signUpOutput.setSignUpStatus(false);
            signUpOutput.setSignUpStatusMessage("Invalid email");
            return signUpOutput;
        }

        User existingUser = userRepo.findFirstByUserEmail(email);
        if (existingUser != null) {
            signUpOutput.setSignUpStatus(false);
            signUpOutput.setSignUpStatusMessage("Email already registered!!!");
            return signUpOutput;
        }

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getPassword());
            user.setPassword(encryptedPassword);

            if (email.endsWith("@admin.com")) {
                user.setRole(UserRole.ADMIN);
            } else if (email.endsWith("@visitor.com")) {
                user.setRole(UserRole.VISITOR);
            } else {
                user.setRole(UserRole.NORMAL_USER);
            }

            userRepo.save(user);

            signUpOutput.setSignUpStatus(true);
            signUpOutput.setSignUpStatusMessage("User registered successfully");
        } catch (NoSuchAlgorithmException e) {
            signUpOutput.setSignUpStatus(false);
            signUpOutput.setSignUpStatusMessage("Internal error occurred during sign up");
        }
        return signUpOutput;
    }

    public String signIn(SignInInput signInInput) {
        String signInStatusMessage = null;
        String email = signInInput.getEmail();
        String password = signInInput.getPassword();

        User existingUser = userRepo.findFirstByUserEmail(email);
        if (existingUser == null) {
            return "Email not registered!!!";
        }

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(password);

            if (existingUser.getPassword().equals(encryptedPassword)) {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authRepo.save(authToken);

                EmailHandler.sendEmail(email,"email testing",authToken.getTokenValue());
                return "Sign in successful";
            } else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        } catch (Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
