package com.softtech.webapp.controller;

import com.softtech.webapp.dto.AddressGetDto;
import com.softtech.webapp.dto.AddressPostDto;
import com.softtech.webapp.entity.*;
import com.softtech.webapp.service.*;
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
@RequestMapping("/api/v1/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private NeighborhoodService neighborhoodService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private ModelMapper modelMapper;

    @Validated
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AddressPostDto addressDto) {
        Address address = new Address();

        Optional<Country> country = countryService.findById(addressDto.getCountryId());
        if(country.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country Doesn't Exist");
        }
        address.setCountry(country.get());

        Optional<City> city = cityService.findById(addressDto.getCityId());
        if(city.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City Doesn't Exist");
        }
        address.setCity(city.get());

        Optional<District> district = districtService.findById(addressDto.getDistrictId());
        if(district.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("District Doesn't Exist");
        }
        address.setDistrict(district.get());

        Optional<Neighborhood> neighborhood = neighborhoodService.findById(addressDto.getNeighborhoodId());
        if(neighborhood.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Neighborhood Doesn't Exist");
        }
        address.setNeighborhood(neighborhood.get());

        Optional<Street> street = streetService.findById(addressDto.getStreetId());
        if(street.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Street Doesn't Exist");
        }
        address.setStreet(street.get());

        address.setBuildingNo(addressDto.getBuildingNo());
        address.setFlatNo(addressDto.getFlatNo());
        address = addressService.save(address);
        return ResponseEntity.ok(modelMapper.map(address, AddressGetDto.class));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);
        if(address.isPresent())
            return ResponseEntity.ok(modelMapper.map(address.get(), AddressGetDto.class));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Doesn't Exist");
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Address> addressList = addressService.findAll();
        List<AddressGetDto> addressGetDtoList = addressList.stream().map(address -> modelMapper.map(address, AddressGetDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(addressGetDtoList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);
        if(address.isPresent()) {
            addressService.delete(address.get());
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Doesn't Exist");
    }
}
