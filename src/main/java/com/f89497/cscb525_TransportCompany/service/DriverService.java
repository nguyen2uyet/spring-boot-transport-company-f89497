package com.f89497.cscb525_TransportCompany.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f89497.cscb525_TransportCompany.entity.Driver;
import com.f89497.cscb525_TransportCompany.repository.DriverRepository;

@Service
public class DriverService extends AbstractSevice<Driver>{
    
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver findOneById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ivalid note id: " + id));
        return driver;
    }

    @Override
    public List<Driver> list() {
        return driverRepository.findAll();
    }

    public List<Driver> listOrderByClass() {
        return driverRepository.findAll(Sort.by(Sort.Direction.ASC, "qualification"));
    }

    public List<Driver> listOrderBySalary() {
        return driverRepository.findAll(Sort.by(Sort.Direction.ASC, "salary"));
    }

    @Override
    public void add(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public void update(Driver driver) {
        ModelMapper mapper = new ModelMapper();
        Driver oldDriver = findOneById(driver.getId());
        oldDriver = mapper.map(driver,Driver.class);
        driverRepository.save(oldDriver);
    }

    

}
