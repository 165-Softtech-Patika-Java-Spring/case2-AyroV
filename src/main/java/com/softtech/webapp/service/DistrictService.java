package com.softtech.webapp.service;

import com.softtech.webapp.dao.DistrictDao;
import com.softtech.webapp.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {
    @Autowired
    private DistrictDao districtDao;

    public District save(District district) {
        return districtDao.save(district);
    }

    public Optional<District> findById(Long id) {
        return districtDao.findById(id);
    }

    public List<District> findAllByCityId(Long id) {
        return districtDao.findAllByCityId(id);
    }

    public List<District> findAll() {
        return districtDao.findAll();
    }
}
