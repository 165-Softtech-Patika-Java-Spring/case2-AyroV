package com.softtech.webapp.dto;

import lombok.Data;

@Data
public class DistrictGetDto {
    private Long id;
    private String name;
    private Long cityId;
    private String cityName;
}
