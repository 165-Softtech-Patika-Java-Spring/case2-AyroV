package com.softtech.webapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CityPostDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Plate No cannot be null")
    private Integer plateNo;
    @NotNull(message = "Country Id cannot be null")
    private Long countryId;
}
