package com.f89497.cscb525_TransportCompany.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name="transportations")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="start_point",nullable = false)
    private String startPoint;

    @Column(name="end_point",nullable = false)
    private String endPoint;

    @Column(name="depature_time",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date depatureTime;

    @Column(name="arrival_time",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalTime;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private TransportCompany company;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;
}
