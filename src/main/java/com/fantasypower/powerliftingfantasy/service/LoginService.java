package com.fantasypower.powerliftingfantasy.service;

import com.fantasypower.powerliftingfantasy.entity.AppUser;
import com.fantasypower.powerliftingfantasy.model.LoginRequest;
import com.fantasypower.powerliftingfantasy.model.LoginResponse;
import com.fantasypower.powerliftingfantasy.repository.AppUserRepository;
import com.fantasypower.powerliftingfantasy.repository.JwtTokenRepository;
//import com.fantasypower.powerliftingfantasy.security.config.JwtService;
import com.fantasypower.powerliftingfantasy.security.config.JwtUtils;
import com.fantasypower.powerliftingfantasy.util.PasswordEncode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    // private final PasswordEncoder passwordEncoder;

    private final AppUserRepository appUserRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncode passwordEncode;


    public LoginResponse login(LoginRequest request) {
        String token = null;
        AppUser user = appUserRepository.findByEmail(request.getEmail()).orElseThrow();
        boolean isUserEnabled = user.getIsEnabled();
        if (user != null) {
            if(userPasswordCheck(request.getPassword(), user) && isUserEnabled == true){
            token = jwtUtils.generateToken(user);
            return LoginResponse.builder().token(token).user(user).build();
        }}
        return (LoginResponse) ResponseEntity.badRequest();
    }

    public boolean userPasswordCheck(String password, AppUser user) {
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        String encodedPassword = user.getPassword();
        return passencoder.matches(password, encodedPassword);
    }
}
