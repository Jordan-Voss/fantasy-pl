// package com.fantasypower.powerliftingfantasy.security.config;
//
// import com.fantasypower.powerliftingfantasy.appuser.AppUserService;
// import lombok.AllArgsConstructor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.security.web.util.matcher.OrRequestMatcher;
// import org.springframework.security.web.util.matcher.RequestMatcher;
//
// @Configuration
// @AllArgsConstructor
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class WebSecurityConfig {
//
// private final AppUserService appUserService;
// private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
// private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
// new AntPathRequestMatcher("/api/v1/auth/register/**"),
// new AntPathRequestMatcher("/api/v*/auth/confirm/**")
// );
// @Bean
// public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws
// Exception {
// return authenticationConfiguration.getAuthenticationManager();
// }
//
// @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// http.csrf().disable();
// return http.build();
// }
// }