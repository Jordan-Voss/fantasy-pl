package com.fantasypower.powerliftingfantasy.security.config;

import com.fantasypower.powerliftingfantasy.entity.AppUser;
import com.fantasypower.powerliftingfantasy.model.AppUserRole;
import com.fantasypower.powerliftingfantasy.repository.AppUserRepository;
import com.fantasypower.powerliftingfantasy.service.AppUserService;
import com.fantasypower.powerliftingfantasy.util.PasswordEncode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

import static com.fantasypower.powerliftingfantasy.service.AppUserService.USER_NOT_FOUND_EMAIL;
import static com.fantasypower.powerliftingfantasy.service.AppUserService.USER_NOT_FOUND_USERNAME;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final AppUserService appUserService;

    private final PasswordEncode passwordEncode;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers("/api/v1/auth/**").permitAll().anyRequest()
                .authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncode.pwEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(appUserService);
        authProvider.setPasswordEncoder(passwordEncode.pwEncoder());
        return new ProviderManager(authProvider);
    }
}
