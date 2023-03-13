package com.fantasypower.powerliftingfantasy.model;

import com.fantasypower.powerliftingfantasy.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    AppUser user;

    String token;
}
