package com.bankapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionData {

    private double transactionDate;
    private double amount;
    private String senderAccount;

    public TransactionData() {

    }

    public TransactionData(double transactionDate, double amount, String senderAccount) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.senderAccount = senderAccount;
    }

    public double getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(double transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

}
