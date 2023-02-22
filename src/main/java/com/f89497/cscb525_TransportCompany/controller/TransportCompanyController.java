package com.f89497.cscb525_TransportCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.f89497.cscb525_TransportCompany.entity.TransportCompany;
import com.f89497.cscb525_TransportCompany.service.TransportCompanyService;

@Controller
public class TransportCompanyController extends AbstractController<TransportCompany>{

    @Autowired
    private TransportCompanyService transportCompanyService;
    
    @GetMapping("/companies")
    public String companies(Model model){
        List<TransportCompany> companies = transportCompanyService.list();
        model.addAttribute("companies",companies);
        return "company/companies";
    }

    @GetMapping("/company/add")
    public String showFormNewCompany(Model model){
        model.addAttribute("company",new TransportCompany());
        return "company/new-company";
    }

    @PostMapping("/company/add")
    public String add(@ModelAttribute TransportCompany company,Model model){
        transportCompanyService.add(company);
        return "redirect:/companies";
    }

    @GetMapping("/company/edit/{id}")
    public String edit(@PathVariable("id") String stdId,Model model){
        Long id = Long.parseLong(stdId);
        model.addAttribute("company", transportCompanyService.findOneById(id));
        return"company/edit-company";
    }

    @GetMapping("/company/delete/{id}")
    public String delete(@PathVariable("id") String stdId,Model model){
        Long id = Long.parseLong(stdId);
        transportCompanyService.delete(id);
        return"redirect:/companies";
    }

    @PostMapping("/company/update")
    public String update(@ModelAttribute TransportCompany company,Model model){
        transportCompanyService.update(company);
        return "redirect:/companies";
    }

    @GetMapping("/company/sort/by-name")
    public String sortCompaniesByName(Model model){
        model.addAttribute("companies",transportCompanyService.listOderByName());
        return "/company/companies";
    }

    @GetMapping("/company/sort/by-income")
    public String sortCompaniesByIncome(Model model){
        model.addAttribute("companies",transportCompanyService.listOderByIncome());
        return "/company/companies";
    }

}
