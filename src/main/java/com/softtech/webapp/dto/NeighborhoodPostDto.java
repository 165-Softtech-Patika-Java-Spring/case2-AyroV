package com.softtech.webapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class NeighborhoodPostDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "District Id cannot be null")
    private Long districtId;
}
