package com.softtech.webapp.controller;

import com.softtech.webapp.dto.CityGetDto;
import com.softtech.webapp.dto.CityPostDto;
import com.softtech.webapp.dto.DistrictGetDto;
import com.softtech.webapp.entity.City;
import com.softtech.webapp.entity.Country;
import com.softtech.webapp.entity.District;
import com.softtech.webapp.service.CityService;
import com.softtech.webapp.service.CountryService;
import com.softtech.webapp.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private ModelMapper modelMapper;

    @Validated
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CityPostDto cityDto) {
        Optional<Country> country = countryService.findById(cityDto.getCountryId());
        if(country.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country Doesn't Exist");
        }
        City city = modelMapper.map(cityDto, City.class);
        city = cityService.save(city);
        return ResponseEntity.ok(modelMapper.map(city, CityGetDto.class));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<City> city = cityService.findById(id);
        if(city.isPresent())
            return ResponseEntity.ok(modelMapper.map(city.get(), CityGetDto.class));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City Doesn't Exist");
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false) Integer plateNo) {
        if(plateNo != null) {
            Optional<City> city = cityService.findByPlateNo(plateNo);
            if(city.isPresent())
                return ResponseEntity.ok(modelMapper.map(city.get(), CityGetDto.class));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City Doesn't Exist");
        }
        List<City> cityList = cityService.findAll();
        List<CityGetDto> cityGetDtoList = cityList.stream().map(city -> modelMapper.map(city, CityGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(cityGetDtoList);
    }

    @GetMapping("{id}/districts")
    public ResponseEntity<?> findAllByCityId(@PathVariable Long id) {
        List<District> districtList = districtService.findAllByCityId(id);
        List<DistrictGetDto> districtGetDtoList = districtList.stream().map(district -> modelMapper.map(district, DistrictGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(districtGetDtoList);
    }
}
