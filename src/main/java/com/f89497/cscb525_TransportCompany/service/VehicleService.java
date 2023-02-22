package com.f89497.cscb525_TransportCompany.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f89497.cscb525_TransportCompany.entity.Vehicle;
import com.f89497.cscb525_TransportCompany.repository.VehicleRepository;

@Service
public class VehicleService extends AbstractSevice<Vehicle>{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle findOneById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ivalid vehicle id: " + id));
        return vehicle;
    }

    @Override
    public List<Vehicle> list() {
        return vehicleRepository.findAll();
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public void update(Vehicle vehicle) {
        ModelMapper mapper = new ModelMapper();
        Vehicle oldVehicle = findOneById(vehicle.getId());
        oldVehicle = mapper.map(vehicle,Vehicle.class);
        vehicleRepository.save(oldVehicle);
    }
    
}
