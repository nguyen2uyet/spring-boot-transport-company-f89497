package com.f89497.cscb525_TransportCompany.entity;

import java.util.List;

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

@Data
@Entity
@Table(name="drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name",nullable = false)
    private String name;

    @Column(name="age",nullable = false)
    private int age;

    @Column(name="salary",nullable = false)
    private double salary;

    @Column(name="qualification",nullable = false)
    private String qualification;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private TransportCompany company;

    @OneToMany(mappedBy = "driver")
    private List<Transportation> transportations;
}
