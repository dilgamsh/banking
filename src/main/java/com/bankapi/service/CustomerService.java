package com.bankapi.service;

import com.bankapi.generic.GenericService;
import com.bankapi.model.Customer;

public interface CustomerService extends GenericService<Customer> {

    public boolean checkAvail(String clientIdentifier);

}
