package com.bankapi.controller;

import com.bankapi.model.Customer;
import com.bankapi.model.Employee;
import com.bankapi.model.Response;
import com.bankapi.service.CustomerService;
import com.bankapi.service.EmployeeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;

@Path("/login")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class LoginController {

    private  final EmployeeService employeeService;
    private  final CustomerService customerService;

    Employee loggedInUser = null;
    Customer loggedInCustomer = null;

    @Inject
    public LoginController(EmployeeService employeeService, CustomerService customerService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
    }

    @POST
    @Path("/employeeLogin")
    public Response loginEmployee(Employee p) {
        Response response = new Response();
        if (p == null || p.getEmployeeId().isEmpty() || p.getPassword().isEmpty()) {
            response.setStatus(false);
            response.setMessage("Enter employee id and the password correctly!");
            return response;
        }
        if (!employeeService.authenticate(p)) {
            response.setStatus(false);
            response.setMessage("Invalid credentials!");
            return response;
        }
        loggedInUser = this.employeeService.find(p.getEmployeeId());
        this.employeeService.setLoggedInUser(loggedInUser);
        response.setStatus(true);
        response.setMessage("Login successfull");
        return response;
    }

    @POST
    @Path("/customerLogin")
    public Response loginCustomer(Customer p) {
        Response response = new Response();
        if (p == null || p.getClientIdentifier().isEmpty() || p.getPassword().isEmpty()) {
            response.setStatus(false);
            response.setMessage("Enter client id and the password correctly!");
            return response;
        }
        if (!customerService.authenticate(p)) {
            response.setStatus(false);
            response.setMessage("Invalid credentials!");
            return response;
        }
        loggedInCustomer = this.customerService.find(p.getClientIdentifier());
        this.customerService.setLoggedInUser(loggedInCustomer);
        response.setStatus(true);
        response.setMessage("Login successfull");
        return response;
    }

}
