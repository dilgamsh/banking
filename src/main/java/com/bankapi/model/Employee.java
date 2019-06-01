package com.bankapi.model;

import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {

    private static final int INITIAL_ID = 5;
    AtomicLong counter;
    private Long id;
    private String employeeId;
    private String names;
    private String password;
    private String role;
    private boolean status;

    public Employee() {
        this.counter = new AtomicLong(INITIAL_ID);
        setId();
    }

    public Employee(String employeeId, String names, String password, String role, boolean status) {
        this.counter = new AtomicLong(INITIAL_ID);
        this.id = counter.longValue();
        this.employeeId = employeeId;
        this.names = names;
        this.password = password;
        this.role = role;
        this.status = status;
    }    

    public Long getId() {
        return id;
    }

    private void setId() {
        this.id = counter.longValue();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

}
