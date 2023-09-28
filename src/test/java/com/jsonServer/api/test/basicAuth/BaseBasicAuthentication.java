package com.jsonServer.api.test.basicAuth;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseBasicAuthentication {

    public RequestSpecification getBasicAuth(){
        return given()
                .baseUri("https://apingweb.com")
                .basePath("/api/auth")
                .auth().basic("admin","12345")
                .header("Content-Type","application/json");
    }
}
