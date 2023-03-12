package com.fantasypower.powerliftingfantasy.service;

import com.fantasypower.powerliftingfantasy.entity.AppUser;
import com.fantasypower.powerliftingfantasy.model.LoginRequest;
import com.fantasypower.powerliftingfantasy.model.LoginResponse;
import com.fantasypower.powerliftingfantasy.repository.AppUserRepository;
import com.fantasypower.powerliftingfantasy.model.AppUserRole;
import com.fantasypower.powerliftingfantasy.repository.JwtTokenRepository;
//import com.fantasypower.powerliftingfantasy.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final PasswordEncoder passwordEncoder;

//    private final AuthenticationManager authenticationManager;

    private final AppUserRepository appUserRepository;

//    private final JwtService jwtService;

    private final JwtTokenRepository jwtTokenRepository;

    private final RegistrationService registrationService;

    public LoginResponse login(LoginRequest request) {
//        UsernamePasswordAuthenticationToken tkn = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(),Collections.singleton(new SimpleGrantedAuthority(AppUserRole.ROLE_USER.name()))
//        );
//        authenticationManager.authenticate(
//                tkn
//        );
        AppUser user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow();
//        String jwtToken = jwtService.generateJwtTokenNoExtraClaims(user);
//        revokeAllUserTokens(user);
//        registrationService.saveUserToken(user, jwtToken);
        return LoginResponse.builder().token("G").build();
    }
}
