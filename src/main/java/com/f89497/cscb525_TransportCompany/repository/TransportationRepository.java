package com.f89497.cscb525_TransportCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f89497.cscb525_TransportCompany.entity.Transportation;

public interface TransportationRepository extends JpaRepository<Transportation,Long> {
    
}
