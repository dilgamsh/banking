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
import javax.inject.Inject;

@Path("/employee")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
public class EmployeeController {

    private final EmployeeService employeeService;
    Employee loggedInUser=null;

    @Inject
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @POST
    @Path("/add")
    public Response addEmployee(Employee p) {
        Response response = new Response();
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
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

    @GET
    @Path("/{employeeId}/delete")
    public Response deleteEmployee(@PathParam("employeeId") String employeeId) {
        Response response = new Response();
        if (checkLoginStatus()) {
            response.setStatus(false);
            response.setMessage("You need to login first");
            return response;
        }
        employeeService.delete(employeeId);
        response.setStatus(true);
        response.setMessage("Employee deleted successfully");
        return response;
    }

    @GET
    @Path("/{employeeId}/get")
    public Employee getEmployee(@PathParam("employeeId") String employeeId) {
        if (checkLoginStatus()) {
            return null;
        }
        return employeeService.find(employeeId);
    }

    @Path("/{employeeId}/getDummyEmployee")
    public Employee getDummyEmployee(@PathParam("employeeId") String employeeId) {
        Employee p = new Employee();
        p.setEmployeeId(employeeId);
        p.setNames("Jacob Juma");
        p.setRole("Teller");
        p.setStatus(true);
        return p;
    }

    @GET
    @Path("/getAll")
    public Employee[] getAllEmployees() {
        if (checkLoginStatus()) {
            return null;
        }
        return employeeService.findAll();
    }

    public boolean checkLoginStatus() {
        return this.employeeService.getLoggedInUser() == null;
    }

}
