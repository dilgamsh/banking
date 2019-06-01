package com.bankapi.controller;

import com.bankapi.model.Account;
import com.bankapi.model.Transaction;
import com.bankapi.model.Response;
import com.bankapi.model.TransactionData;
import com.bankapi.service.AccountService;
import com.bankapi.service.CustomerService;
import com.bankapi.service.TransactionService;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

@Path("/transaction")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class TransactionController {

    private final AccountService accountService;
    private final CustomerService customerService;
    private final TransactionService transactionService;

    String loggedIn;

    @Inject
    public TransactionController(AccountService accountService, CustomerService customerService, TransactionService transactionService) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @POST
    @Path("/debitAccount")
    public Response debitAccount(Transaction p) {
        Response response = new Response();
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
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
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
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
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
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
    public Transaction[] getAllTransactions() {
        if (checkLoginStatus()) {

        }
        return transactionService.findAll();
    }

    @GET
    @Path("/{transactionId}/get")
    public Transaction getTransaction(@PathParam("transactionId") String transactionId) {
        return transactionService.find(transactionId);
    }

    @GET
    @Path("/{transactionId}/getDummyTransaction")
    public Transaction getDummyTransaction(@PathParam("transactionId") String transactionId) {
        Transaction p = new Transaction();        
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
        
        loggedIn = this.customerService.getLoggedInUser().getClientIdentifier();
        if(!loggedIn.equals(sender.getClientIdentifier())){
         response.setStatus(false);   
         response.setMessage("Transaction failed, Sender account not found!");
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
        if (accountService.update(sender) != null || accountService.update(recipient) != null) {
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
        String convid = String.valueOf("BANK" + randomNum);
        return convid;
    }

    public boolean checkLoginStatus() {
        return this.customerService.getLoggedInUser() == null;
    }
}
