package com.bankapi.controller;

import com.bankapi.model.Account;
import com.bankapi.model.Response;
import com.bankapi.service.AccountService;
import com.bankapi.service.CustomerService;
import com.bankapi.service.EmployeeService;
import java.util.logging.Logger;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/account")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    @Inject
    public AccountController(AccountService accountService, CustomerService customerService, EmployeeService employeeService) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());

    @POST
    @Path("/regularAccount")
    public Response regularAccount(Account p) {
        Response response = new Response();
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
        if (customerService.find(p.getClientIdentifier()) == null) {
            response.setStatus(false);
            response.setMessage("Client Identifier Does Not Exist");
            return response;
        }
        LOG.info(customerService.find(p.getClientIdentifier()).toString());
        if (accountService.find(p.getAccountIdentifier()) != null) {
            response.setStatus(false);
            response.setMessage("Account Already Exists");
            return response;
        }
        p.setEmployeeId(this.employeeService.getLoggedInUser().getEmployeeId());
        accountService.save(p);
        response.setStatus(true);
        response.setMessage("Account created successfully");
        return response;
    }

    @POST
    @Path("/internationalAccount")
    public Response internationalAccount(Account p) {
        Response response = new Response();
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
        if (accountService.find(p.getAccountIdentifier()) != null) {
            response.setStatus(false);
            response.setMessage("Account Already Exists");
            return response;
        }
        p.setEmployeeId(this.employeeService.getLoggedInUser().getEmployeeId());
        accountService.save(p);
        response.setStatus(true);
        response.setMessage("Account created successfully");
        return response;
    }

    @POST
    @Path("/savingAccount")
    public Response savingAccount(Account p) {
        Response response = new Response();
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
        if (accountService.find(p.getAccountIdentifier()) != null) {
            response.setStatus(false);
            response.setMessage("Account Already Exists");
            return response;
        }
        p.setEmployeeId(this.employeeService.getLoggedInUser().getEmployeeId());
        accountService.save(p);
        response.setStatus(true);
        response.setMessage("Account created successfully");
        return response;
    }

    @GET
    @Path("/{accountIdentifier}/delete")
    public Response deleteAccount(@PathParam("accountIdentifier") String accountIdentifier) {
        Response response = new Response();
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
        if (accountService.find(accountIdentifier) == null) {
            response.setStatus(false);
            response.setMessage("Account Doesn't Exists");
            return response;
        }
        accountService.delete(accountIdentifier);
        response.setStatus(true);
        response.setMessage("Account deleted successfully");
        return response;
    }

    @GET
    @Path("/{accountIdentifier}/get")
    public Account getAccount(@PathParam("accountIdentifier") String accountIdentifier) {
        if (checkLoginStatus()) {
            return null;
        }
        return accountService.find(accountIdentifier);
    }

    @GET
    @Path("/{accountIdentifier}/getDummyAccount")
    public Account getDummyAccount(@PathParam("accountIdentifier") String accountIdentifier) {
        Account p = new Account();
        p.setAccountIdentifier("12123");
        p.setAccountType("savings account");
        p.setBankCode("1231");
        p.setCurrentAmount(50000);
        p.setAccountIdentifier(accountIdentifier);
        return p;
    }

    @GET
    @Path("/getAll")
    public Account[] getAllAccounts() {
        if (checkLoginStatus()) {
            return null;
        }
        return accountService.findAll();
    }

    public boolean checkLoginStatus() {
        return this.employeeService.getLoggedInUser() == null;
    }

}
