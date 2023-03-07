package com.fantasypower.powerliftingfantasy.auth;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRequest {
    private final String email;

    private final String username;

    private final String password;

}