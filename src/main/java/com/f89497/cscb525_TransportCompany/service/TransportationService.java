package com.f89497.cscb525_TransportCompany.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f89497.cscb525_TransportCompany.entity.Transportation;
import com.f89497.cscb525_TransportCompany.repository.TransportationRepository;

@Service
public class TransportationService extends AbstractSevice<Transportation>{

    @Autowired
    private TransportationRepository transportationRepository;

    @Override
    public Transportation findOneById(Long id) {
        Transportation transportation = transportationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + id));
        return transportation;
    }

    @Override
    public List<Transportation> list() {
        return transportationRepository.findAll();
    }

    @Override
    public void add(Transportation transportation) {
        transportationRepository.save(transportation);
    }

    @Override
    public void delete(Long id) {
        transportationRepository.deleteById(id);
    }

    @Override
    public void update(Transportation transportation) {
        ModelMapper mapper = new ModelMapper();
        Transportation oldTransportation = findOneById(transportation.getId());
        oldTransportation = mapper.map(transportation,Transportation.class);
        transportationRepository.save(oldTransportation);
    }
    
}
