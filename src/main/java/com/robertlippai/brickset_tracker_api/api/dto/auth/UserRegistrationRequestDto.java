package com.robertlippai.brickset_tracker_api.api.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {
    private String fullName;
    private String username;
    private String password;
}
