package com.softtech.webapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StreetPostDto {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Neighborhood Id cannot be null")
    private Long neighborhoodId;
}
