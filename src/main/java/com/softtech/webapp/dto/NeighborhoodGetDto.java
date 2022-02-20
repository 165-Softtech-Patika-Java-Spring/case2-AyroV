package com.softtech.webapp.dto;

import lombok.Data;

@Data
public class NeighborhoodGetDto {
    private Long id;
    private String name;
    private Long districtId;
    private String districtName;
}
