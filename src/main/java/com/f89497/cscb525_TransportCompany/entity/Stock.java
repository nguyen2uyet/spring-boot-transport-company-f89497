package com.f89497.cscb525_TransportCompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender",nullable = false)
    private String sender;

    @Column(name = "receiver",nullable = false)
    private String receiver;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "cash_on_delivery",nullable = false)
    private double cashOnDelivery;

    @OneToOne(mappedBy = "stock")
    private Transportation transportation;

}
