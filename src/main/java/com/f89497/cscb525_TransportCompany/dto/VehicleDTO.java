package com.f89497.cscb525_TransportCompany.dto;

import lombok.Data;

@Data
public class VehicleDTO {
    private Long id;
    private String plate;
    private double price;
    private String brand;
    private double mass;
    private Long company_id;
    private String company_name;

}
