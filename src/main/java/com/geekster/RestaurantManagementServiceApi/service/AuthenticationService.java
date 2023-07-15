package com.geekster.RestaurantManagementServiceApi.service;

import com.geekster.RestaurantManagementServiceApi.model.AuthenticationToken;
import com.geekster.RestaurantManagementServiceApi.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthRepo authRepo;

    public boolean authenticate(String email, String token) {
        AuthenticationToken authenticationToken = authRepo.findFirstByTokenValue(token);

        if(authenticationToken == null) {
            return  false;
        }
        String tokenConnectedEmail = authenticationToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }
}
