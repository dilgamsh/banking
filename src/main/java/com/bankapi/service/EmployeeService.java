package com.bankapi.service;

import com.bankapi.generic.GenericService;
import com.bankapi.model.Employee;

public interface EmployeeService extends GenericService<Employee> {

    public boolean checkAvail(String employeeId);

    public boolean authenticate(Employee p);

    public Employee getLoggedInUser();

    public void setLoggedInUser(Employee loggedInUser);
}
