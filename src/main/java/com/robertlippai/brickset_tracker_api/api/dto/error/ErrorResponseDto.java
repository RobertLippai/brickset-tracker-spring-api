package com.robertlippai.brickset_tracker_api.api.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private Integer statusCode;
    private String message;
    private long timestamp;
}
