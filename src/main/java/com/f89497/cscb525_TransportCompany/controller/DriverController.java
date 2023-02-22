package com.f89497.cscb525_TransportCompany.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.f89497.cscb525_TransportCompany.dto.DriverDTO;
import com.f89497.cscb525_TransportCompany.entity.Driver;
import com.f89497.cscb525_TransportCompany.entity.Transportation;
import com.f89497.cscb525_TransportCompany.service.DriverService;
import com.f89497.cscb525_TransportCompany.service.TransportCompanyService;

@Controller
public class DriverController extends AbstractController<DriverDTO>{
    
    @Autowired
    private DriverService driverService;

    @Autowired
    private TransportCompanyService transportCompanyService;

    @GetMapping("/drivers")
    public String drivers(Model model){
        model.addAttribute("drivers", driverService.list());
        return "driver/drivers";
    }

    @GetMapping("/driver/add")
    public String showFormNewDriver(Model model){
        model.addAttribute("driver", new DriverDTO());
        model.addAttribute("companies",transportCompanyService.list());
        return "driver/new-driver";
    }

    @Override
    @PostMapping("/driver/add")
    public String add(@ModelAttribute DriverDTO newDriver, Model model) {
        ModelMapper mapper = new ModelMapper();
        Driver driver = mapper.map(newDriver, Driver.class);
        driver.setCompany(transportCompanyService.findOneById(newDriver.getCompany_id()));
        driverService.add(driver);
        return "redirect:/drivers";
    }

    @Override
    @PostMapping("/driver/update")
    public String update(@ModelAttribute DriverDTO updateDriver, Model model) {
        ModelMapper mapper = new ModelMapper();
        Driver driver = mapper.map(updateDriver,Driver.class);
        driver.setCompany(transportCompanyService.findOneById(updateDriver.getCompany_id()));
        driverService.update(driver);
        return "redirect:/drivers";
    }

    @Override
    @GetMapping("/driver/delete/{id}")
    public String delete(@PathVariable("id") String stdId, Model model) {
        Long id =  Long.parseLong(stdId);
        driverService.delete(id);
        return "redirect:/drivers";
    }

    @Override
    @GetMapping("/driver/edit/{id}")
    public String edit(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        ModelMapper mapper = new ModelMapper();
        DriverDTO driverDTO = mapper.map(driverService.findOneById(id),DriverDTO.class);
        driverDTO.setCompany_id(driverService.findOneById(id).getCompany().getId());
        model.addAttribute("driver",driverDTO);
        model.addAttribute("companies",transportCompanyService.list());
        return "driver/edit-driver";
    }

    @GetMapping("/driver/sort/by-class")
    public String sortDriverByClass(Model model){
        model.addAttribute("drivers", driverService.listOrderByClass());
        return "/driver/drivers";
    }
    
    @GetMapping("/driver/sort/by-salary")
    public String sortDriverBySalary(Model model){
        model.addAttribute("drivers", driverService.listOrderBySalary());
        return "/driver/drivers";
    }

    @GetMapping("/driver/transportations/{id}")
    public String showTransportationsOfADriver(@PathVariable("id") String strID, Model model){
        Long id = Long.parseLong(strID);
        List<Transportation> transportations = driverService.findOneById(id).getTransportations();
        model.addAttribute("transportations",transportations);
        return "/driver/transportations-of-driver";
    }



}
