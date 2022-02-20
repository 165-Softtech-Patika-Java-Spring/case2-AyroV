package com.softtech.webapp.dto;

import lombok.Data;

@Data
public class CountryGetDto {
    private Long id;
    private String name;
    private Integer countryCode;
}
