package com.bankapi.model;

import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Login {

    private static final int INITIAL_ID = 1;
    private final  AtomicLong counter = new AtomicLong(INITIAL_ID);
    private Long id;
    private String employeeId;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
