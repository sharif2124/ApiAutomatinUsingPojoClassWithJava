package com.jsonServer.api.test;


import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {

    public RequestSpecification getBase(){
        return given()
                .baseUri("http://localhost")
                .port(3000)
                .basePath("");



    }
}
