package com.bankapi.controller;

import com.bankapi.model.Employee;
import com.bankapi.model.Response;
import com.bankapi.service.EmployeeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import javax.inject.Inject;

@Path("/employee")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class EmployeeController {

    private final EmployeeService employeeService;

    @Inject
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    Employee loggedInUser = null;
    
    private static final int INITIAL_ID = 1;
    private final AtomicLong counter = new AtomicLong(INITIAL_ID);

    @POST
    @Path("/add")
    public Response addEmployee(Employee p) {
        Response response = new Response();
        if (employeeService.find(p.getEmployeeId()) != null) {
            response.setStatus(false);
            response.setMessage("Employee Already Exists");
            return response;
        }
       
        employeeService.save(p);
        response.setStatus(true);
        response.setMessage("Employee created successfully");
        return response;
    }

    @POST
    @Path("/login")
    public Response loginEmployee(Employee p) {
        Response response = new Response();
        if (!employeeService.authenticate(p)) {
            response.setStatus(false);
            response.setMessage("Invalid credentials!");
            return response;
        }
        loggedInUser=getEmployee(p.getEmployeeId());
        loggedInUser.setStatus(true);
        employeeService.save(loggedInUser);
        response.setStatus(true);
        response.setMessage("Login successfull");
        return response;
    }

    @GET
    @Path("/{employeeId}/delete")
    public Response deleteEmployee(@PathParam("employeeId") String employeeId) {
        Response response = new Response();
        employeeService.delete(employeeId);
        response.setStatus(true);
        response.setMessage("Employee deleted successfully");
        return response;
    }

    @GET
    @Path("/{employeeId}/get")
    public Employee getEmployee(@PathParam("employeeId") String employeeId) {
        return employeeService.find(employeeId);
    }

    
    @Path("/{employeeId}/getDummyEmployee")
    public Employee getDummyEmployee(@PathParam("employeeId") String employeeId) {
        Employee p = new Employee();
        p.setId(Long.valueOf(1234));
        p.setEmployeeId(employeeId);
        p.setRole("Teller");
        p.setStatus(true);
        return p;
    }

    @GET
    @Path("/getAll")
    public Employee[] getAllEmployees() {
        return employeeService.findAll();
    }

}
