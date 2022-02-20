package com.softtech.webapp.controller;

import com.softtech.webapp.dto.CityGetDto;
import com.softtech.webapp.dto.CountryGetDto;
import com.softtech.webapp.dto.CountryPostDto;
import com.softtech.webapp.entity.City;
import com.softtech.webapp.entity.Country;
import com.softtech.webapp.service.CityService;
import com.softtech.webapp.service.CountryService;
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
@RequestMapping("/api/v1/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ModelMapper modelMapper;

    @Validated
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CountryPostDto countryDto) {
        Country country = modelMapper.map(countryDto, Country.class);
        country = countryService.save(country);
        return ResponseEntity.ok(modelMapper.map(country, CountryGetDto.class));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Country> country = countryService.findById(id);
        if(country.isPresent())
            return ResponseEntity.ok(modelMapper.map(country.get(), CountryGetDto.class));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country Doesn't Exist");
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false) Integer countryCode) {
        if(countryCode != null) {
            Optional<Country> country = countryService.findByCountryCode(countryCode);
            if(country.isPresent())
                return ResponseEntity.ok(modelMapper.map(country.get(), CountryGetDto.class));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City Doesn't Exist");
        }
        List<Country> countryList = countryService.findAll();
        List<CountryGetDto> countryGetDtoList = countryList.stream().map(country -> modelMapper.map(country, CountryGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(countryGetDtoList);
    }

    @GetMapping("{id}/cities")
    public ResponseEntity<?> findAllByCountryId(@PathVariable Long id) {
        List<City> cityList = cityService.findAllByCountryId(id);
        List<CityGetDto> cityGetDtoList = cityList.stream().map(city -> modelMapper.map(city, CityGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(cityGetDtoList);
    }
}
