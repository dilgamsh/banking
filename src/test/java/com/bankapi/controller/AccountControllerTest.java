package com.bankapi.controller;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.testng.annotations.Test;

public class AccountControllerTest {

 
    @After
    public void registerDebit() {
        Response response = given().
                contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body("<account>\n" +
"   <bankCode>EQT</bankCode>\n" +
"   <clientIdentifier>12341</clientIdentifier>\n" +
"   <accountIdentifier>EQT1222URT</accountIdentifier>\n" +
"   <currentAmount>5000.00</currentAmount>\n" +
"   <accountType>regular</accountType>\n" +
"</account>")
                .when()
                .post("http://127.0.0.1:8080/banking/account/regularAccount");
        System.out.println("POST Response\n" + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
