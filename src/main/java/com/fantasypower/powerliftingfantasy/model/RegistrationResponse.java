package com.fantasypower.powerliftingfantasy.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    String confirmationToken;

    String jwtToken;
}
