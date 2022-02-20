package com.softtech.webapp.controller;

import com.softtech.webapp.dto.*;
import com.softtech.webapp.entity.City;
import com.softtech.webapp.entity.Country;
import com.softtech.webapp.entity.District;
import com.softtech.webapp.entity.Neighborhood;
import com.softtech.webapp.service.CityService;
import com.softtech.webapp.service.DistrictService;
import com.softtech.webapp.service.NeighborhoodService;
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
@RequestMapping("/api/v1/districts")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private CityService cityService;
    @Autowired
    private NeighborhoodService neighborhoodService;
    @Autowired
    private ModelMapper modelMapper;

    @Validated
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid DistrictPostDto districtDto) {
        Optional<City> city = cityService.findById(districtDto.getCityId());
        if(city.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City Doesn't Exist");
        }
        District district = modelMapper.map(districtDto, District.class);
        district = districtService.save(district);
        return ResponseEntity.ok(modelMapper.map(district, DistrictGetDto.class));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<District> district = districtService.findById(id);
        if(district.isPresent())
            return ResponseEntity.ok(modelMapper.map(district.get(), DistrictGetDto.class));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("District Doesn't Exist");
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<District> districtList = districtService.findAll();
        List<DistrictGetDto> districtGetDtoList = districtList.stream().map(district -> modelMapper.map(district, DistrictGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(districtGetDtoList);
    }

    @GetMapping("{id}/neighborhoods")
    public ResponseEntity<?> findAllByDistrictId(@PathVariable Long id) {
        List<Neighborhood> neighborhoodList = neighborhoodService.findAllByDistrictId(id);
        List<NeighborhoodGetDto> neighborhoodGetDtoList = neighborhoodList.stream().map(neighborhood -> modelMapper.map(neighborhood, NeighborhoodGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(neighborhoodGetDtoList);
    }
}
