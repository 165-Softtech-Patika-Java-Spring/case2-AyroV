package com.softtech.webapp.service;

import com.softtech.webapp.dao.StreetDao;
import com.softtech.webapp.entity.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreetService {
    @Autowired
    private StreetDao streetDao;

    public Street save(Street street) {
        return streetDao.save(street);
    }

    public void update(String name, Long id) {
        streetDao.setStreetNameById(name, id);
    }

    public Optional<Street> findById(Long id) {
        return streetDao.findById(id);
    }

    public List<Street> findAllByNeighborhoodId(Long id) {
        return streetDao.findAllByNeighborhoodId(id);
    }

    public List<Street> findAll() {
        return streetDao.findAll();
    }
}
