package com.fantasypower.powerliftingfantasy.controller;

import com.fantasypower.powerliftingfantasy.model.LoginRequest;
import com.fantasypower.powerliftingfantasy.model.LoginResponse;
import com.fantasypower.powerliftingfantasy.model.RegistrationRequest;
import com.fantasypower.powerliftingfantasy.model.RegistrationResponse;
import com.fantasypower.powerliftingfantasy.service.RegistrationService;
import com.fantasypower.powerliftingfantasy.service.LoginService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest request)
            throws MessagingException {
        return ResponseEntity.ok(registrationService.register(request));
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/logins")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(loginService.login(request));
        }
    }
