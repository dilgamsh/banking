package com.bankapi.model;

import java.util.concurrent.atomic.AtomicLong;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

    private static final int INITIAL_ID = 1;
    AtomicLong counter;
    private Long id;
    @NotNull(message = "Client Identifier is a required field")
    @Size(min = 3, max = 6, message = "Client Identifier must be equal to or greater than 3 characters and less than 6 characters")
    private String clientIdentifier;
    @NotNull(message = "Password is a required field")
    @Size(min = 3, max = 6, message = "Password must be equal to or greater than 3 characters and less than 6 characters")
    private String password;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String joinDate;
    private String employeeId;
    private boolean status;
 private String bankName="DILGAM BANK";
 
    public Customer() {
         this.counter = new AtomicLong(INITIAL_ID);
         setId();
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Customer(String clientIdentifier, String password, String firstName, String lastName, String birthDate, String joinDate, String employeeId, boolean status) {
        this.counter = new AtomicLong(INITIAL_ID);
        this.id = counter.longValue();
        this.setBankName(bankName);
        this.clientIdentifier = clientIdentifier;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
        this.employeeId = employeeId;
        this.status = status;
    }

    public String getBankName() {
        return bankName;
    }

    public Long getId() {
        return id;
    }

    private void setId() {
        this.id = counter.longValue();
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
