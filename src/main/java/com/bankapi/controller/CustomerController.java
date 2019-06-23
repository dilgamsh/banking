package com.bankapi.controller;

import com.bankapi.model.Customer;
import com.bankapi.model.Response;
import com.bankapi.service.CustomerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.inject.Singleton;

@Path("/customer")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
@Singleton
public class CustomerController {

    private final CustomerService customerService;

    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @POST
    @Path("/add")
    public Response addCustomer(Customer p) {
        Response response = new Response();
        if (customerService.find(p.getClientIdentifier()) != null) {
            response.setStatus(false);
            response.setMessage("Customer Already Exists");
            return response;
        }
       
        customerService.save(p);
        response.setStatus(true);
        response.setMessage("Customer registered successfully");
        return response;
    }

    @GET
    @Path("/{clientIdentifier}/delete")
    public Response deleteCustomer(@PathParam("clientIdentifier") String clientIdentifier) {
        Response response = new Response();
        if (customerService.find(clientIdentifier) == null) {
            response.setStatus(false);
            response.setMessage("Customer Doesn't Exists");
            return response;
        }
        customerService.delete(clientIdentifier);
        response.setStatus(true);
        response.setMessage("Customer deleted successfully");
        return response;
    }

    @GET
    @Path("/{clientIdentifier}/get")
    public Customer getCustomer(@PathParam("clientIdentifier") String clientIdentifier) {
        return customerService.find(clientIdentifier);
    }

    @GET
    @Path("/{clientIdentifier}/getDummyCustomer")
    public Customer getDummyCustomer(@PathParam("clientIdentifier") String clientIdentifier) {
        Customer p = new Customer();
        p.setId(Long.valueOf(1));
        p.setFirstName("Brian");
        p.setLastName("Otieno");
        p.setBirthDate("10/12/1993");
        p.setJoinDate("06/04/2010");
        p.setClientIdentifier("EQT12345US");
        return p;
    }

    @GET
    @Path("/getAll")
    public Customer[] getAllCustomers() {
        return customerService.findAll();
    }

}
