package com.fantasypower.powerliftingfantasy.registration;

import com.fantasypower.powerliftingfantasy.auth.LoginRequest;
import com.fantasypower.powerliftingfantasy.auth.LoginResponse;
import com.fantasypower.powerliftingfantasy.auth.LoginService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest request) throws MessagingException {
        return ResponseEntity.ok(registrationService.register(request));
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }
}
