package com.softtech.webapp.service;

import com.softtech.webapp.dao.CityDao;
import com.softtech.webapp.entity.City;
import com.softtech.webapp.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityDao cityDao;

    public City save(City city) {
        return cityDao.save(city);
    }

    public Optional<City> findById(Long id) {
        return cityDao.findById(id);
    }

    public Optional<City> findByPlateNo(int plateNo) {
        return cityDao.findByPlateNo(plateNo);
    }

    public List<City> findAll() {
        return cityDao.findAll();
    }

    public List<City> findAllByCountryId(Long id) {
        return cityDao.findAllByCountryId(id);
    }
}
