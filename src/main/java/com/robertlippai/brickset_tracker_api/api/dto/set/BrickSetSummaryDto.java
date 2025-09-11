package com.robertlippai.brickset_tracker_api.api.dto.set;

import com.robertlippai.brickset_tracker_api.api.model.BrickSet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrickSetSummaryDto {
    private int sid;
    private String setId;
    private String name;
    private String imageUrl;
    private Integer releaseYear;
    private String brandName;

    public static BrickSetSummaryDto fromEntity(BrickSet brickSet) {
        BrickSetSummaryDto dto = new BrickSetSummaryDto();

        dto.setSid(brickSet.getSid());
        dto.setSetId(brickSet.getSetId());
        dto.setName(brickSet.getName());
        dto.setReleaseYear(brickSet.getReleaseYear());
        dto.setImageUrl(brickSet.getImageUrl());

        if (brickSet.getBrand() != null) {
            dto.setBrandName(brickSet.getBrand().getName());
        }

        return dto;
    }
}
