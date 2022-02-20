package com.softtech.webapp.controller;

import com.softtech.webapp.dto.*;
import com.softtech.webapp.entity.Neighborhood;
import com.softtech.webapp.entity.Street;
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
@RequestMapping("/api/v1/streets")
public class StreetController {
    @Autowired
    private NeighborhoodService neighborhoodService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private ModelMapper modelMapper;

    @Validated
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid StreetPostDto streetDto) {
        Optional<Neighborhood> neighborhood = neighborhoodService.findById(streetDto.getNeighborhoodId());
        if(neighborhood.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Neighborhood Doesn't Exist");
        }
        Street street = modelMapper.map(streetDto, Street.class);
        street = streetService.save(street);
        return ResponseEntity.ok(modelMapper.map(street, StreetGetDto.class));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Street> street = streetService.findById(id);
        if(street.isPresent())
            return ResponseEntity.ok(modelMapper.map(street.get(), StreetGetDto.class));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Street Doesn't Exist");
    }

    @Validated
    @PatchMapping("{id}")
    public ResponseEntity<?> updateName(@RequestBody @Valid StreetUpdateDto updateDto, @PathVariable Long id) {
        streetService.update(updateDto.getName(), id);
        return ResponseEntity.ok().body("Updated");
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Street> streetList = streetService.findAll();
        List<StreetGetDto> streetGetDtoList = streetList.stream().map(street -> modelMapper.map(street, StreetGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(streetGetDtoList);
    }
}
