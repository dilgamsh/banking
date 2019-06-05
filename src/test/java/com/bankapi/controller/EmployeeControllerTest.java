package com.bankapi.controller;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;

public class EmployeeControllerTest {

 
    @After
    public void addEmployee() {
        Response response = given().
                contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body("<employee>\n"
                        + "   <id>1</id>\n"
                        + "   <employeeId>1234</employeeId>\n"
                        + "   <names>Jonah Richards</names>\n"
                        + "   <role>Teller</role>\n"
                        + "   <status>true</status>\n"
                        + "   <password>123</password>\n"
                        + "</employee>")
                .when()
                .post("http://localhost:8080/banking/employee/add");
        System.out.println("POST Response\n" + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
