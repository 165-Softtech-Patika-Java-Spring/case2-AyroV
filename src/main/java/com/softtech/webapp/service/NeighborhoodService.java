package com.softtech.webapp.service;

import com.softtech.webapp.dao.NeighborhoodDao;
import com.softtech.webapp.entity.Neighborhood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NeighborhoodService {
    @Autowired
    private NeighborhoodDao neighborhoodDao;

    public Neighborhood save(Neighborhood neighborhood) {
        return neighborhoodDao.save(neighborhood);
    }

    public void update(String name, Long id) {
         neighborhoodDao.setNeighborhoodNameById(name, id);
    }

    public Optional<Neighborhood> findById(Long id) {
        return neighborhoodDao.findById(id);
    }

    public List<Neighborhood> findAllByDistrictId(Long id) {
        return neighborhoodDao.findAllByDistrictId(id);
    }

    public List<Neighborhood> findAll() {
        return neighborhoodDao.findAll();
    }
}
