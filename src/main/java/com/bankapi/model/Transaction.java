package com.bankapi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {

    private Long id;
    private String transactionId;
    private TransactionData transactionData;
    private String recipientAccount;
    private int numberOfTransaction;
    private String IBAN;

    public Transaction() {
    }

    public Transaction(Long id, String transactionId, TransactionData transactionData, String recipientAccount, int numberOfTransaction, String IBAN) {
        this.id = id;
        this.transactionId = transactionId;
        this.transactionData = transactionData;
        this.recipientAccount = recipientAccount;
        this.numberOfTransaction = numberOfTransaction;
        this.IBAN = IBAN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

}
