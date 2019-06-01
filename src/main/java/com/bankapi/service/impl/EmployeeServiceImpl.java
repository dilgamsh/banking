package com.bankapi.service.impl;

import com.bankapi.dao.DatabaseHandler;
import com.bankapi.model.Employee;
import com.bankapi.service.EmployeeService;
import java.util.Set;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EmployeeServiceImpl implements EmployeeService {
    
    Employee loggedInUser = null;
   private static final Logger LOG = Logger.getLogger(EmployeeServiceImpl.class.getName());
 
    @Inject
    private DatabaseHandler handler;
    
    @Override
    public Employee save(Employee entity) {
     return    handler.getEMPLOYEES().put(entity.getEmployeeId(), entity);        
    }
    
    @Override
    public Employee update(Employee entity) {
      return handler.getEMPLOYEES().put(entity.getEmployeeId(), entity);        
    }
    
    @Override
    public void delete(String employeeId) {
        handler.getEMPLOYEES().remove(employeeId);
    }
    
    @Override
    public Employee[] findAll() {
        Set<String> allEmployees = handler.getEMPLOYEES().keySet();
        Employee[] p = new Employee[allEmployees.size()];
        int i = 0;
        for (String employeeId : allEmployees) {
            p[i] = find(employeeId);
            i++;
        }
        return p;
    }
    
    @Override
    public Employee find(String employeeId) {
        return handler.getEMPLOYEES().get(employeeId);
    }
    
    @Override
    public boolean authenticate(Employee p) {
        loggedInUser = find(p.getEmployeeId());
        if (loggedInUser == null) {
            LOG.info("Employee not registered!");
            return false;
        }
        if (!loggedInUser.getPassword().trim().equals(p.getPassword().trim())) {
            LOG.info("Passwords do not match!");
            return false;
        }
        loggedInUser.setStatus(true);
        update(loggedInUser);
        return true;
    }
    
    @Override
    public boolean checkAvail(String employeeId) {
        return find(employeeId) != null;
    }

    @Override
    public Employee getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void setLoggedInUser(Employee loggedInUser) {
        this.loggedInUser = loggedInUser;
    }


}
