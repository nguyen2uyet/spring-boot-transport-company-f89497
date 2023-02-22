package com.f89497.cscb525_TransportCompany.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.f89497.cscb525_TransportCompany.entity.TransportCompany;
import com.f89497.cscb525_TransportCompany.repository.TransportCompanyRepository;

@Service
public class TransportCompanyService extends AbstractSevice<TransportCompany>{
    
    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

     public TransportCompany findOneById(Long id){
        TransportCompany company = transportCompanyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ivalid note id: " + id));
        return company;
     }

    public List<TransportCompany> list(){
        return transportCompanyRepository.findAll();
    }

    public List<TransportCompany> listOderByName(){
        return transportCompanyRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
    public List<TransportCompany> listOderByIncome(){
        return transportCompanyRepository.findAll(Sort.by(Sort.Direction.ASC, "income"));
    }

    public void add(TransportCompany company){
        transportCompanyRepository.save(company);
    }

    public void delete(Long id){
        transportCompanyRepository.deleteById(id);
    }

    public void update(TransportCompany company){
        ModelMapper mapper = new ModelMapper();
        TransportCompany oldCompany = findOneById(company.getId());
        oldCompany = mapper.map(company,TransportCompany.class);
        transportCompanyRepository.save(oldCompany);
    }

}
