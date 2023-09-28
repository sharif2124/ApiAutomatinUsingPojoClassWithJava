package com.jsonServer.api.test.basicAuth;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTest extends BaseBasicAuthentication{
    @Test
    public void getUserShouldSuccess(){
      given()
              .spec(getBasicAuth())
              .log().uri()
              .when()
              .post("/users")
              .then()
              .statusCode(200)
              .log().body();

    }
}
