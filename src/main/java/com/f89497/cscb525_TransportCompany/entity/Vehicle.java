package com.f89497.cscb525_TransportCompany.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="plate",nullable = false)
    private String plate;

    @Column(name="price",nullable = false)
    private double price;

    @Column(name="brand",nullable = false)
    private String brand;

    @Column(name="mass",nullable = false)
    private double mass;

    @ManyToOne
    @JoinColumn(name="company_id")
    private TransportCompany company;

    @OneToMany(mappedBy = "vehicle")
    private Set<Transportation> transportations;
}
