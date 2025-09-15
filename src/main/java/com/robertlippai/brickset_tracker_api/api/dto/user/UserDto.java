package com.robertlippai.brickset_tracker_api.api.dto.user;

import com.robertlippai.brickset_tracker_api.api.model.Role;
import com.robertlippai.brickset_tracker_api.api.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long uid;
    private String username;
    private Set<Role> roles;

    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();

        dto.setUid(user.getUid());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());

        return dto;
    }
}

