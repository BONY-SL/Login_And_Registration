package com.login.SpringBootLogin.service;


import com.login.SpringBootLogin.dto.UserRegistrationDTO;
import com.login.SpringBootLogin.model.ApplicationUser;
import com.login.SpringBootLogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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


            userRepository.save(applicationUser);
            return "User has been Successfully Created...";
        }
    }
}
