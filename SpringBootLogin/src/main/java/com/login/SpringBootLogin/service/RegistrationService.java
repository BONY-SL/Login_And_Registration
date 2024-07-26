package com.login.SpringBootLogin.service;


import com.login.SpringBootLogin.dto.UserRegistrationDTO;
import com.login.SpringBootLogin.model.ApplicationUser;
import com.login.SpringBootLogin.model.Token;
import com.login.SpringBootLogin.repository.TokenRepository;
import com.login.SpringBootLogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Transactional
    public String userRegistration(UserRegistrationDTO userRegistrationDTO){

        //check the User Email have or not in the database

        boolean userExsist = userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent();

        if(userExsist){

            return "A User Email is Already Exsist...";
        }else {
            //Encode Password
            String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());

            // Map The DTO Class to User Entity

            ApplicationUser applicationUser = ApplicationUser.builder()
                    .firstname(userRegistrationDTO.getFirstname())
                    .lastname(userRegistrationDTO.getLastname())
                    .email(userRegistrationDTO.getEmail())
                    .password(encodedPassword)
                    .role(userRegistrationDTO.getRole())
                    .build();


            ApplicationUser saveUser = userRepository.save(applicationUser);


            //Generate Token

            String generateToken = UUID.randomUUID().toString();

            generateToken = generateToken+userRegistrationDTO.getFirstname()+userRegistrationDTO.getLastname();

            Token token = Token.builder()
                    .token(generateToken)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusMinutes(10))
                    .user(saveUser)
                    .build();

            tokenRepository.save(token);

            return "User has been Successfully Created...\n"+generateToken;
        }
    }
}
