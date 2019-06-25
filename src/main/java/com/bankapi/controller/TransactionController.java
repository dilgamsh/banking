package com.bankapi.controller;

import com.bankapi.model.Account;
import com.bankapi.model.Transaction;
import com.bankapi.model.Response;
import com.bankapi.model.TransactionData;
import com.bankapi.service.AccountService;
import com.bankapi.service.TransactionService;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

@Path("/transaction")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
@Singleton
public class TransactionController {


    private final AccountService accountService;

    private final TransactionService transactionService;

    @Inject
    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @POST
    @Path("/debitAccount")
    public Response debitAccount(Transaction p) {
        Response response = new Response();
        if (transactionService.find(p.getTransactionId()) != null) {
            response.setStatus(false);
            response.setMessage("Transaction Already Exists");
            return response;
        }
        Account senderAccount = accountService.find(p.getTransactionData().getSenderAccount());
        Account recipientAccount = accountService.find(p.getRecipientAccount());
        Double transactionAmount = p.getTransactionData().getAmount();
        response = performTransaction(p, senderAccount, recipientAccount, transactionAmount, response);
        return response;
    }

    @POST
    @Path("/savingAccount")
    public Response savingAccount(Transaction p) {
        Response response = new Response();
        if (transactionService.find(p.getTransactionId()) != null) {
            response.setStatus(false);
            response.setMessage("Transaction Already Exists");
            return response;
        }
        Account senderAccount = accountService.find(p.getTransactionData().getSenderAccount());
        Account recipientAccount = accountService.find(p.getRecipientAccount());
        Double transactionAmount = p.getTransactionData().getAmount();
        response = performTransaction(p, senderAccount, recipientAccount, transactionAmount, response);
        return response;
    }

    @POST
    @Path("/internationalAccount")
    public Response internationalAccount(Transaction p) {
        Response response = new Response();
        if (transactionService.find(p.getTransactionId()) != null) {
            response.setStatus(false);
            response.setMessage("Transaction Already Exists");
            return response;
        }
        Account senderAccount = accountService.find(p.getTransactionData().getSenderAccount());
        Account recipientAccount = accountService.find(p.getRecipientAccount());
        Double transactionAmount = p.getTransactionData().getAmount();
        response = performTransaction(p, senderAccount, recipientAccount, transactionAmount, response);
        return response;
    }


    @GET
    @Path("/getAll")
    public Transaction[] getAllCustomers() {
        return transactionService.findAll();
    }

    @GET
    @Path("/{clientIdentifier}/getDummyTransaction")
    public Transaction getDummyCustomer(@PathParam("clientIdentifier") String clientIdentifier) {
        Transaction p = new Transaction();
        p.setId(Long.valueOf(1));
        p.setIBAN("CER123");
        p.setNumberOfTransaction(1);
        p.setRecipientAccount("1234");
        TransactionData transfer = new TransactionData();
        transfer.setAmount(2000.05);
        transfer.setSenderAccount("1235");
        p.setTransactionData(transfer);
        p.setTransactionId(transactionId());
        return p;
    }

    
    Response performTransaction(Transaction p, Account sender, Account recipient, Double amount, Response response) {
        if (sender == null || recipient == null) {
            response.setStatus(false);
            if (sender == null) {
                response.setMessage("Transaction failed, Sender account not found!");
            } else {
                response.setMessage("Transaction failed, Recipient account not found!");
            }
            return response;
        }
        Double recipientCurrentAmount = recipient.getCurrentAmount();
        Double senderCurrentAmount = sender.getCurrentAmount();
        if (senderCurrentAmount == 0 || senderCurrentAmount <= amount) {
            response.setStatus(false);
            response.setMessage("Transaction failed! The sender account has insufficient funds to transaction " + amount.toString() + "!");
            return response;
        }
        Double senderAmount = senderCurrentAmount - amount;
        sender.setCurrentAmount(senderAmount);
        Double recipientAmount = recipientCurrentAmount + amount;
        recipient.setCurrentAmount(recipientAmount);
        if (accountService.update(sender)!=null || accountService.update(recipient) != null) {
            response.setStatus(true);
            response.setMessage("Transaction successfull");
           
            p.setTransactionId(transactionId());
            transactionService.save(p);
            return response;
        }
        response.setMessage("Transaction failed");
        response.setStatus(false);
        return response;
    }
    
    public static String transactionId() {
        Random rand = new Random();
        int randomNum = rand.nextInt(599999998) + 1;
        String convid = String.valueOf(randomNum);
        return convid;
    }
}
