package com.f89497.cscb525_TransportCompany.dto;

import lombok.Data;

@Data
public class DriverDTO {
    private Long id;
    private String name;
    private int age;
    private String qualification;
    private double salary;
    private String company_name;
    private Long company_id;
}
