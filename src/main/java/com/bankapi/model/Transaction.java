package com.bankapi.model;

import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {

    private String bankName = "DILGAM BANK";
    private static final int INITIAL_ID = 5;
    AtomicLong counter;
    private Long id;
    private String transactionId;
    private TransactionData transactionData;
    private String recipientAccount;
    private int numberOfTransaction;
    private String IBAN;
    private String employeeId;
    private String transactionDate;

    public Transaction() {
        this.counter = new AtomicLong(INITIAL_ID);
        setId();
        this.setBankName(bankName);
        this.id = this.counter.getAndIncrement();
    }

    public Transaction(Long id, String transactionId, TransactionData transactionData, String recipientAccount, int numberOfTransaction, String IBAN, String transactionDate) {
        this.counter = new AtomicLong(INITIAL_ID);
        setId();
        this.transactionId = transactionId;
        this.transactionData = transactionData;
        this.recipientAccount = recipientAccount;
        this.numberOfTransaction = numberOfTransaction;
        this.IBAN = IBAN;
        this.transactionDate = transactionDate;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionData getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionData transactionData) {
        this.transactionData = transactionData;
    }

    public String getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(String recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public int getNumberOfTransaction() {
        return numberOfTransaction;
    }

    public void setNumberOfTransaction(int numberOfTransaction) {
        this.numberOfTransaction = numberOfTransaction;
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
