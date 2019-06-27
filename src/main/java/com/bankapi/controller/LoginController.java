package com.bankapi.controller;

import com.bankapi.model.Employee;
import com.bankapi.model.Response;
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

    private final EmployeeService employeeService;

    @Inject
    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    Employee loggedInUser = null;

    @POST
    @Path("/employeeLogin")
    public Response loginEmployee(Employee p) {
        Response response = new Response();
        if (!employeeService.authenticate(p)) {
            response.setStatus(false);
            response.setMessage("Invalid credentials!");
            return response;
        }
        loggedInUser = this.employeeService.find(p.getEmployeeId());
        response.setStatus(true);
        response.setMessage("Login successfull");
        return response;
    }

}
