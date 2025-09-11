package com.robertlippai.brickset_tracker_api.api.dto.brand;

import com.robertlippai.brickset_tracker_api.api.model.BrickBrand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrickBrandDto {
    private int bid;
    private String name;
    private String description;

    public static BrickBrandDto fromEntity(BrickBrand brickBrand) {
        BrickBrandDto dto = new BrickBrandDto();

        dto.setBid(brickBrand.getBid());
        dto.setName(brickBrand.getName());
        dto.setDescription(brickBrand.getDescription());

        return dto;
    }
}
