package com.f89497.cscb525_TransportCompany.dto;

import lombok.Data;

@Data
public class StockDTO {
    private Long id;
    private String sender;
    private String receiver;
    private String type;
    private double cashOnDelivery;
}
