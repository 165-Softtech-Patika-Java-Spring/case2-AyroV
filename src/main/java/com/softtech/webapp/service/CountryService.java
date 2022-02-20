package com.softtech.webapp.service;

import com.softtech.webapp.dao.CountryDao;
import com.softtech.webapp.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryDao countryDao;

    public Country save(Country country) {
        return countryDao.save(country);
    }

    public Optional<Country> findById(Long id) {
        return countryDao.findById(id);
    }

    public Optional<Country> findByCountryCode(Integer countryCode) {
        return countryDao.findByCountryCode(countryCode);
    }

    public List<Country> findAll() {
        return countryDao.findAll();
    }
}
