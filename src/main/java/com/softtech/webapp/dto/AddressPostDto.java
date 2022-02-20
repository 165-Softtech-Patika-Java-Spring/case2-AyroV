package com.softtech.webapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressPostDto {
    @NotNull(message = "Country Id cannot be null")
    private Long countryId;
    @NotNull(message = "City Id cannot be null")
    private Long cityId;
    @NotNull(message = "District Id cannot be null")
    private Long districtId;
    @NotNull(message = "Neighborhood Id cannot be null")
    private Long neighborhoodId;
    @NotNull(message = "Street Id cannot be null")
    private Long streetId;
    @NotNull(message = "Building No cannot be null")
    private Integer buildingNo;
    @NotNull(message = "Flat No cannot be null")
    private Integer flatNo;
}
