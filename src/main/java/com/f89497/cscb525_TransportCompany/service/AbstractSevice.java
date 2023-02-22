package com.f89497.cscb525_TransportCompany.service;

import java.util.List;

abstract class AbstractSevice<T> {
    public abstract T findOneById(Long id);
    public abstract List<T> list();
    public abstract void add(T t);
    public abstract void delete(Long id);
    public abstract void update(T t);
    
}
