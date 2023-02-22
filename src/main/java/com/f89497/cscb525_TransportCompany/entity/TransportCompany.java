package com.f89497.cscb525_TransportCompany.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="companies")
public class TransportCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="income",nullable = false)
    private Double income;

    @OneToMany(mappedBy="company")
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy="company")
    private List<Driver> drivers;

    @OneToMany(mappedBy="company")
    private List<Driver> transportations;

}
