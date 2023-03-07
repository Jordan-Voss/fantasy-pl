package com.fantasypower.powerliftingfantasy.registration;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    String confirmationToken;

    String jwtToken;
}
