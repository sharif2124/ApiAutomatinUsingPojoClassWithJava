package com.jsonServer.api.test.bearerToken;

import com.jsonServer.api.test.bearerToken.pojo.Login;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public abstract class BaseBearerTokenApiTest {




    public RequestSpecification getServerInfo(){
       return given()
               .baseUri("https://apingweb.com")
               .basePath("/api");

    }

    public RequestSpecification getBearerTokenSpec(){
        return  given()
                .spec(getServerInfo())
                 .header("Content-Type","application/json")
                .header("Authorization","Bearer " +getBearerToken());

    }

    private String getBearerToken(){
        return    given()
                .spec(getServerInfo())
                .body(new Login("superman@gmail.com","123456"))
                .log().uri()
                .log().body()
                .when()
                .post("/login")
                .then()
                .log().body()
                .statusCode(200)
                .body("message",equalTo("Login success"))

                .extract().jsonPath().getString("token");
    }

}
