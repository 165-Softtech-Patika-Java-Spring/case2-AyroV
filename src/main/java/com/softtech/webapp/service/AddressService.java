package com.softtech.webapp.service;

import com.softtech.webapp.dao.AddressDao;
import com.softtech.webapp.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressDao addressDao;

    public Address save(Address address) {
        return addressDao.save(address);
    }

    public Optional<Address> findById(Long id) {
        return addressDao.findById(id);
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }

    public void delete(Address address) {
        addressDao.delete(address);
    }
}
