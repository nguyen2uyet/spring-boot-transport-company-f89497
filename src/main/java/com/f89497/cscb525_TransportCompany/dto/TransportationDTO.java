package com.f89497.cscb525_TransportCompany.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TransportationDTO {
    private Long id;
    private String startPoint;
    private String endPoint;
    private Date depatureTime;
    private Date arrivalTime;
    private Long company_id;
    private Long driver_id;
    private Long vehicle_id;
    private String sender;
    private String receiver;
    private String type;
    private Long stock_id;
    private double cashOnDelivery;
}
