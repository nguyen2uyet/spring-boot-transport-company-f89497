package com.f89497.cscb525_TransportCompany.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.f89497.cscb525_TransportCompany.dto.StockDTO;
import com.f89497.cscb525_TransportCompany.entity.Stock;
import com.f89497.cscb525_TransportCompany.service.StockService;
@Controller
public class StockController extends AbstractController<StockDTO>{

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public String stocks(Model model){
        model.addAttribute("stocks", stockService.list());
        return "stock/stocks";
    }

    @GetMapping("/stock/add")
    public String showFormNewStock(Model model){
        model.addAttribute("stock",new StockDTO());
        return "stock/new-stock";
    }

    @Override
    @PostMapping("/stock/add")
    public String add(@ModelAttribute StockDTO stockDTO, Model model) {
        ModelMapper mapper = new ModelMapper();
        Stock stock = mapper.map(stockDTO,Stock.class);
        stockService.add(stock);
        return "redirect:/stocks";
    }

    @Override
    @PostMapping("/stock/update")
    public String update(@ModelAttribute StockDTO stockDTO, Model model) {
        ModelMapper mapper = new ModelMapper();
        Stock stock = mapper.map(stockDTO,Stock.class);
        stockService.update(stock);
        return "redirect:/stocks";
    }

    @Override
    @GetMapping("/stock/delete/{id}")
    public String delete(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        stockService.delete(id);
        return "redirect:/stocks";
    }

    @Override
    @GetMapping("/stock/edit/{id}")
    public String edit(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        ModelMapper mapper = new ModelMapper();
        StockDTO stock = mapper.map(stockService.findOneById(id),StockDTO.class);
        model.addAttribute("stock", stock);
        return "stock/edit-stock";
    }
    
}
