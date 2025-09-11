package com.robertlippai.brickset_tracker_api.api.dto.tag;

import com.robertlippai.brickset_tracker_api.api.model.BrickTag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrickTagDto {
    private Integer tid;
    private String name;

    public static BrickTagDto fromEntity(BrickTag brickTag) {
        BrickTagDto dto = new BrickTagDto();

        dto.setTid(brickTag.getTid());
        dto.setName(brickTag.getName());

        return dto;
    }
}
