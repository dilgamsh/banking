package com.bankapi.service.impl;

import com.bankapi.dao.DatabaseHandler;
import com.bankapi.model.Customer;
import com.bankapi.service.CustomerService;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public abstract class CustomerServiceImpl implements CustomerService {
    
    @Inject
    private DatabaseHandler handler;
    
       
    Customer loggedInUser = null;

    @Override
    public Customer save(Customer entity) {
        return handler.getCUSTOMERS().put(entity.getClientIdentifier(), entity);
        
    }

    @Override
    public Customer update(Customer entity) {
      return   handler.getCUSTOMERS().put(entity.getClientIdentifier(), entity);
        
    }

    @Override
    public void delete(String clientIdentifier) {
        handler.getCUSTOMERS().remove(clientIdentifier);

    }

    @Override
    public Customer find(String clientIdentifier) {
        return handler.getCUSTOMERS().get(clientIdentifier);
    }

    @Override
    public Customer[] findAll() {
        Set<String> allCustomers = handler.getCUSTOMERS().keySet();
        Customer[] p = new Customer[allCustomers.size()];
        int i = 0;
        for (String clientIdentifier : allCustomers) {
            p[i] = handler.getCUSTOMERS().get(clientIdentifier);
            i++;
        }
        return p;
    }

    @Override
    public boolean checkAvail(String clientIdentifier) {
        return handler.getCUSTOMERS().get(clientIdentifier) != null;
    }
 @Override
    public Customer getLoggedInUser() {
        return loggedInUser;
    }
 @Override
    public void setLoggedInUser(Customer loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
