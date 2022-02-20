package com.softtech.webapp.dao;

import com.softtech.webapp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityDao extends JpaRepository<City, Long> {
    Optional<City> findByPlateNo(int plateNo);
    List<City> findAllByCountryId(Long id);
}
