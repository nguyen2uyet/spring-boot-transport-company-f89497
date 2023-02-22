package com.f89497.cscb525_TransportCompany.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.f89497.cscb525_TransportCompany.dto.VehicleDTO;
import com.f89497.cscb525_TransportCompany.entity.Vehicle;
import com.f89497.cscb525_TransportCompany.service.TransportCompanyService;
import com.f89497.cscb525_TransportCompany.service.VehicleService;

@Controller
public class VehicleController extends AbstractController<VehicleDTO>{

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TransportCompanyService transportCompanyService;

    @GetMapping("/vehicles")
    public String vehicles(Model model){
        model.addAttribute("vehicles",vehicleService.list());
        return "vehicle/vehicles";
    }
    @GetMapping("/vehicle/add")
    public String showFormNewVehicle(Model model){
        model.addAttribute("vehicle", new VehicleDTO());
        model.addAttribute("companies", transportCompanyService.list());
        return "vehicle/new-vehicle";
    }

    @Override
    @PostMapping("/vehicle/add")
    public String add(@ModelAttribute VehicleDTO vehicle, Model model) {
        ModelMapper mapper = new ModelMapper();
        Vehicle newVehicle = mapper.map(vehicle, Vehicle.class);
        newVehicle.setCompany(transportCompanyService.findOneById(vehicle.getCompany_id()));
        vehicleService.add(newVehicle);
        return "redirect:/vehicles";
    }

    @Override
    @PostMapping("/vehicle/update")
    public String update(@ModelAttribute VehicleDTO vehicleDTO, Model model) {
        ModelMapper mapper = new ModelMapper();
        Vehicle vehicle = mapper.map(vehicleDTO,Vehicle.class);
        vehicle.setCompany(transportCompanyService.findOneById(vehicleDTO.getCompany_id()));
        vehicleService.update(vehicle);
        return "redirect:/vehicles";
    }

    @Override
    @GetMapping("/vehicle/delete/{id}")
    public String delete(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        vehicleService.delete(id);
        return "redirect:/vehicles";
    }

    @Override
    @GetMapping("/vehicle/edit/{id}")
    public String edit(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        ModelMapper mapper = new ModelMapper();
        VehicleDTO vehicleDTO = mapper.map(vehicleService.findOneById(id),VehicleDTO.class);
        vehicleDTO.setCompany_name(vehicleService.findOneById(id).getCompany().getName());
        vehicleDTO.setCompany_id(vehicleService.findOneById(id).getCompany().getId());
        model.addAttribute("vehicle",vehicleDTO);
        model.addAttribute("companies", transportCompanyService.list());
        return "vehicle/edit-vehicle";
    }
    
   
    
}
