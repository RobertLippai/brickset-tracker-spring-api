package com.robertlippai.brickset_tracker_api.api.dto;

import com.robertlippai.brickset_tracker_api.api.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long uid;
    private String username;
    private String role;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();

        dto.setUid(user.getUid());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());

        return dto;
    }
}

