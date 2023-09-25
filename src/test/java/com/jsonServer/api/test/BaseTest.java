package com.jsonServer.api.test;


import io.restassured.RestAssured;
import org.junit.BeforeClass;

public abstract class BaseTest {

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI= "http://localhost";
//        RestAssured.baseURI= "http://localhost:3000/";
        RestAssured.port= 3000;
        RestAssured.basePath= "";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
