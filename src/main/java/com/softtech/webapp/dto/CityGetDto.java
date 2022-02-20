package com.softtech.webapp.dto;

import lombok.Data;

@Data
public class CityGetDto {
    private Long id;
    private String name;
    private int plateNo;
    private Long countryId;
    private String countryName;
}
