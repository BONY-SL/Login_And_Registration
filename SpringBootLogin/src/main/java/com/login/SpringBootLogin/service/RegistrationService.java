package com.login.SpringBootLogin.service;


import com.login.SpringBootLogin.dto.UserRegistrationDTO;
import com.login.SpringBootLogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    public String userRegistration(UserRegistrationDTO userRegistrationDTO){

        
        return null;
    }
}
