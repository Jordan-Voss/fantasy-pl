package com.fantasypower.powerliftingfantasy.controller;

import com.fantasypower.powerliftingfantasy.entity.AppUser;
import com.fantasypower.powerliftingfantasy.exception.ResourceNotFoundException;
import com.fantasypower.powerliftingfantasy.repository.AppUserRepository;
import com.fantasypower.powerliftingfantasy.security.CurrentUser;
import com.fantasypower.powerliftingfantasy.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AppUserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public AppUser getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
