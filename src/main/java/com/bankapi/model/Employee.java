package com.bankapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {

    private Long id;
    private String employeeId;
    private String password;
    private String role;
    private boolean status;

    public Employee() {
    }

    public Employee(Long id, String employeeId, String password, String role, boolean status) {
        this.id = id;
        this.employeeId = employeeId;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
