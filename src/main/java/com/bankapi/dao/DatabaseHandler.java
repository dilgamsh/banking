package com.bankapi.dao;

import com.bankapi.model.Account;
import com.bankapi.model.Customer;
import com.bankapi.model.Employee;
import com.bankapi.model.Transaction;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class DatabaseHandler {

    public final Map<String, Transaction> TRANSACTIONS = new HashMap<>();

    public final Map<String, Account> ACCOUNTS = new HashMap<>();

    public final Map<String, Customer> CUSTOMERS = new HashMap<>();

    public final Map<String, Employee> EMPLOYEES = new HashMap<>();

    public Map<String, Transaction> getTRANSACTIONS() {
        return TRANSACTIONS;
    }

    public Map<String, Account> getACCOUNTS() {
        return ACCOUNTS;
    }

    public Map<String, Customer> getCUSTOMERS() {
        return CUSTOMERS;
    }

    public Map<String, Employee> getEMPLOYEES() {
        return EMPLOYEES;
    }

}
