package com.softtech.webapp.dto;

import lombok.Data;

@Data
public class StreetGetDto {
    private Long id;
    private String name;
    private Long neighborhoodId;
    private String neighborhoodName;
}
