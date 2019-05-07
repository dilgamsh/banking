package com.bankapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

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

    public Customer() {
    }

    public Customer(Long id, String clientIdentifier, String password, String firstName, String lastName, String birthDate, String joinDate) {
        this.id = id;
        this.clientIdentifier = clientIdentifier;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
