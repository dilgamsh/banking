package com.bankapi.dao;

import com.bankapi.model.Account;
import com.bankapi.model.Customer;
import com.bankapi.model.Employee;
import com.bankapi.model.Transaction;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Singleton;

@Singleton
public class DatabaseHandler {

    AtomicLong counter;
    private static final int INITIAL_ID =1;

    public DatabaseHandler() {
        this.counter = new AtomicLong(INITIAL_ID);
        
        //Initial Employeye data
        //Employee(String employeeId, String names, String password, String role, boolean status)
        EMPLOYEES.put("2002", new Employee("2002","Brandon Owen","abcd","Teller",false));
        EMPLOYEES.put("2003", new Employee("2003","John Rabbits","abcd","Teller",false));
        EMPLOYEES.put("2004", new Employee("2004","Donald Trump","abcd","Director",false));
        
        //Initial Custoomers
        //Customer(String clientIdentifier, String password, String firstName, String lastName, String birthDate, String joinDate, String employeeId, boolean status)
        CUSTOMERS.put("T1200US",new Customer("EQT1200US","abcd","Brian","Otieno","10/12/1993","06/04/2010","2002",true));
        CUSTOMERS.put("EQT1300US",new Customer("EQT1300US","abcd","Jones","Davis","10/12/1990","06/04/2010","2002",true));
        
      }
    
    // java map save data using key and value related to a key e.g((1,Brian)

    public final Map<String, Transaction> TRANSACTIONS = new HashMap<>();

    public final Map<String, Account> ACCOUNTS = new HashMap<>();

    public final Map<String, Customer> CUSTOMERS = new HashMap<>();

    public final Map<String, Employee> EMPLOYEES = new HashMap<>();

    public final Map<String, Employee> LOGIN = new HashMap<>();

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

    public Map<String, Employee> getLOGIN() {
        return LOGIN;
    }

}
