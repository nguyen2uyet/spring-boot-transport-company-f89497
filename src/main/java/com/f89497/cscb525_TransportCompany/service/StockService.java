package com.f89497.cscb525_TransportCompany.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.f89497.cscb525_TransportCompany.entity.Stock;
import com.f89497.cscb525_TransportCompany.repository.StockRepository;

@Service
public class StockService extends AbstractSevice<Stock>{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock findOneById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + id));
        return stock;
    }

    @Override
    public List<Stock> list() {
        return stockRepository.findAll();
    }

    public Stock addAndReturnStock(Stock stock) {
        Stock returnStock = stockRepository.save(stock);
        return returnStock;
    }


    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public void update(Stock newStock) {
        ModelMapper mapper = new ModelMapper();
        Stock oldStock = findOneById(newStock.getId());
        oldStock = mapper.map(newStock,Stock.class);
        stockRepository.save(oldStock);
    }

    @Override
    public void add(Stock t) {
        stockRepository.save(t);
    }
    
}
