package com.softtech.webapp.controller;

import com.softtech.webapp.dto.*;
import com.softtech.webapp.entity.District;
import com.softtech.webapp.entity.Neighborhood;
import com.softtech.webapp.entity.Street;
import com.softtech.webapp.service.DistrictService;
import com.softtech.webapp.service.NeighborhoodService;
import com.softtech.webapp.service.StreetService;
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
@RequestMapping("/api/v1/neighborhoods")
public class NeighborhoodController {
    @Autowired
    private NeighborhoodService neighborhoodService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private ModelMapper modelMapper;

    @Validated
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid NeighborhoodPostDto neighborhoodDto) {
        Optional<District> district = districtService.findById(neighborhoodDto.getDistrictId());
        if(district.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("District Doesn't Exist");
        }
        Neighborhood neighborhood = modelMapper.map(neighborhoodDto, Neighborhood.class);
        neighborhood = neighborhoodService.save(neighborhood);
        return ResponseEntity.ok(modelMapper.map(neighborhood, NeighborhoodGetDto.class));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Neighborhood> neighborhood = neighborhoodService.findById(id);
        if(neighborhood.isPresent())
            return ResponseEntity.ok(modelMapper.map(neighborhood.get(), NeighborhoodGetDto.class));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Neighborhood Doesn't Exist");
    }

    @Validated
    @PatchMapping("{id}")
    public ResponseEntity<?> updateName(@RequestBody @Valid NeighborhoodUpdateDto updateDto, @PathVariable Long id) {
        neighborhoodService.update(updateDto.getName(), id);
        return ResponseEntity.ok().body("Updated");
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Neighborhood> neighborhoodList = neighborhoodService.findAll();
        List<NeighborhoodGetDto> neighborhoodGetDtoList = neighborhoodList.stream().map(neighborhood -> modelMapper.map(neighborhood, NeighborhoodGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(neighborhoodGetDtoList);
    }

    @GetMapping("{id}/streets")
    public ResponseEntity<?> findAllByNeighborhoodId(@PathVariable Long id) {
        List<Street> streetList = streetService.findAllByNeighborhoodId(id);
        List<StreetGetDto> streetGetDtoList = streetList.stream().map(street -> modelMapper.map(street, StreetGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(streetGetDtoList);
    }
}
