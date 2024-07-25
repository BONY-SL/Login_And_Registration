package com.login.SpringBootLogin.controller;

import com.login.SpringBootLogin.dto.UserRegistrationDTO;
import com.login.SpringBootLogin.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/MySpring/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> userRegistration(
            @RequestBody UserRegistrationDTO userRegistrationDTO
            )
    {
        return ResponseEntity.ok(registrationService.userRegistration(userRegistrationDTO));
    }
}
