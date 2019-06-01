package com.bankapi.service;

import com.bankapi.generic.GenericService;
import com.bankapi.model.Customer;

public interface CustomerService extends GenericService<Customer> {

    public boolean checkAvail(String customerId);

    public boolean authenticate(Customer p);

    public Customer getLoggedInUser();

    public void setLoggedInUser(Customer loggedInUser);

}
