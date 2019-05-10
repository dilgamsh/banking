package com.bankapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

    private Long id;
    private String bankCode;
    private String clientIdentifier;
    private String accountIdentifier;
    private double currentAmount;
    private String accountType;
    private String IBAN;

    public Account() {
    }

    public Account(Long id, String bankCode, String clientIdentifier, String accountIdentifier, double currentAmount, String accountType) {
        this.id = id;
        this.bankCode = bankCode;
        this.clientIdentifier = clientIdentifier;
        this.accountIdentifier = accountIdentifier;
        this.currentAmount = currentAmount;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier(String accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

}
