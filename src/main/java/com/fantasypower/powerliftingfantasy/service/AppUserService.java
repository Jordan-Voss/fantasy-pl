package com.fantasypower.powerliftingfantasy.service;

import com.fantasypower.powerliftingfantasy.entity.AppUser;
import com.fantasypower.powerliftingfantasy.entity.ConfirmationToken;
import com.fantasypower.powerliftingfantasy.repository.AppUserRepository;
import com.fantasypower.powerliftingfantasy.util.PasswordEncode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    private final PasswordEncode passwordEncoder;

    private final ConfirmationTokenService confirmationTokenService;

    private final static String USER_NOT_FOUND_USERNAME = "User not found with username %s";
    private final static String USER_NOT_FOUND_EMAIL = "User not found with email %s";

    @Override
    public AppUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_USERNAME, username)));
    }

    public AppUser loadUserByEmail(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_EMAIL, email)));
    }

    public String registerUser(AppUser appUser) {
        boolean emailExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        boolean usernameExists = appUserRepository.findByUsername(appUser.getUsername()).isPresent();
        if (usernameExists || emailExists) {
            return "taken";
        }
        String encodedPassword = passwordEncoder.pwEncoder().encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, appUser, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15));

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }
}
