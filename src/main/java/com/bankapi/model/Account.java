package com.bankapi.model;

import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

    private static final int INITIAL_ID = 1;
    AtomicLong counter;
    private String bankName = "DILGAM BANK";

    private Long id;
    private String bankCode;
    private String clientIdentifier;
    private String accountIdentifier;
    private double currentAmount;
    private String accountType;
    private String IBAN;
    private String employeeId;

    public Account(Account acc) {
        this.counter = new AtomicLong(INITIAL_ID);
        setId();
    }

    public Account() {
        this.counter = new AtomicLong(INITIAL_ID);
        setId();
        this.setBankName(bankName);
        this.id = this.counter.getAndIncrement();
    }

    public Account(String bankCode, String clientIdentifier, String accountIdentifier, double currentAmount, String accountType, String employeeId) {
        this.counter = new AtomicLong(INITIAL_ID);
        setId();
        this.bankCode = bankCode;
        this.clientIdentifier = clientIdentifier;
        this.accountIdentifier = accountIdentifier;
        this.currentAmount = currentAmount;
        this.accountType = accountType;
        this.employeeId = employeeId;
        this.setBankName(bankName);
        this.id = this.counter.getAndIncrement();
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

}
