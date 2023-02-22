package com.f89497.cscb525_TransportCompany.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.f89497.cscb525_TransportCompany.dto.TransportationDTO;
import com.f89497.cscb525_TransportCompany.entity.Stock;
import com.f89497.cscb525_TransportCompany.entity.Transportation;
import com.f89497.cscb525_TransportCompany.service.DriverService;
import com.f89497.cscb525_TransportCompany.service.StockService;
import com.f89497.cscb525_TransportCompany.service.TransportCompanyService;
import com.f89497.cscb525_TransportCompany.service.TransportationService;
import com.f89497.cscb525_TransportCompany.service.VehicleService;

@Controller
public class TransportationController extends AbstractController<TransportationDTO>{

    @Autowired
    private TransportationService transportationService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TransportCompanyService transportCompanyService;

    @Autowired
    private StockService stockService;

    @GetMapping("/transportations")
    public String transportations(Model model){
        model.addAttribute("transportations",transportationService.list());
        return "transportation/transportations";
    }

    @GetMapping("/transportation/add")
    public String showFormNewTransportation(Model model){
        model.addAttribute("transportation", new TransportationDTO());
        model.addAttribute("drivers",driverService.list());
        model.addAttribute("vehicles",vehicleService.list());
        model.addAttribute("companies",transportCompanyService.list()); 
        return "transportation/new-transportation";
    }
    
    
    @Override
    @PostMapping("/transportation/add")
    public String add(TransportationDTO transportationDTO, Model model) {
        ModelMapper mapper = new ModelMapper();
        Transportation transportation = mapper.map(transportationDTO,Transportation.class);
        Stock newStock = new Stock();
        newStock.setSender(transportationDTO.getSender());
        newStock.setReceiver(transportationDTO.getReceiver());
        newStock.setType(transportationDTO.getType());
        newStock.setCashOnDelivery(transportationDTO.getCashOnDelivery());
        transportation.setStock(stockService.addAndReturnStock(newStock));
        transportation.setDriver(driverService.findOneById(transportationDTO.getDriver_id()));
        transportation.setVehicle(vehicleService.findOneById(transportationDTO.getVehicle_id()));
        transportation.setCompany(transportCompanyService.findOneById(transportationDTO.getCompany_id()));
        transportationService.add(transportation);
        return "redirect:/transportations";
    }

    @Override
    @PostMapping("/transportation/update")
    public String update(TransportationDTO transportationDTO, Model model) {
        ModelMapper mapper = new ModelMapper();
        Transportation transportation = mapper.map(transportationDTO,Transportation.class);
        transportation.setDriver(driverService.findOneById(transportationDTO.getDriver_id()));
        transportation.setVehicle(vehicleService.findOneById(transportationDTO.getVehicle_id()));
        transportation.setCompany(transportCompanyService.findOneById(transportationDTO.getCompany_id()));
        transportationService.update(transportation);
        return "redirect:/transportations";
    }

    @Override
    @GetMapping("/transportation/delete/{id}")
    public String delete(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        transportationService.delete(id);
        return "redirect:/transportations";
    }

    @Override
    @GetMapping("/transportation/edit/{id}")
    public String edit(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        ModelMapper mapper = new ModelMapper();
        Transportation transportation = transportationService.findOneById(id);
        TransportationDTO transportationDTO = mapper.map(transportation,TransportationDTO.class);
        transportationDTO.setCompany_id(transportation.getCompany().getId());
        transportationDTO.setDriver_id(transportation.getDriver().getId());
        transportationDTO.setVehicle_id(transportation.getVehicle().getId());
        model.addAttribute("transportation", transportationDTO);
        model.addAttribute("drivers",driverService.list());
        model.addAttribute("vehicles",vehicleService.list());
        model.addAttribute("companies",transportCompanyService.list());
        return "transportation/edit-transportation";
    }
    
}
