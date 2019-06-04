package com.bankapi.controller;



import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;

public class CustomerControllerTest {

    @After
    public void loginEmployee() {
        Response response = given().
                contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body("<employee>\n"
                        + "   <employeeId>2002</employeeId>\n"
                        + "   <password>abcd</password>\n"
                        + "</employee>")
                .when()
                .post("http://localhost:8080/banking/login/employeeLogin");
        System.out.println("POST Response\n" + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

   @After
    public void addCustomer() {
        Response response = given().
                contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body("<customer>\n"
                        + "   <clientIdentifier>1234</clientIdentifier>\n"
                        + "   <firstName>Brian</firstName>  \n"
                        + "   <password>1234</password> \n"
                        + "   <lastName>Jacobs</lastName>\n"
                        + "   <birthDate>06/04/1993</birthDate>\n"
                        + "   <joinDate>21/11/2010</joinDate>\n"
                        + "   <status>21/11/2010</status>\n"
                        + "</customer>")
                .when()
                .post("http://localhost:8080/banking/customer/add");
        System.out.println("POST Response\n" + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
