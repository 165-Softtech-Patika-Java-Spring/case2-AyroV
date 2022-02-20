package com.softtech.webapp.dto;
import lombok.Data;

@Data
public class AddressGetDto {
    private Long id;
    private Long countryId;
    private String countryName;
    private Long cityId;
    private String cityName;
    private Long districtId;
    private String districtName;
    private Long neighborhoodId;
    private String neighborhoodName;
    private Long streetId;
    private String streetName;
    private Integer buildingNo;
    private Integer flatNo;
}
