package com.bankapi.controller;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;

public class LoginControllerTest {

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

}
