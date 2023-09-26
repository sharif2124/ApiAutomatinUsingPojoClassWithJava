package com.jsonServer.api.test.bearerToken;

import com.jsonServer.api.test.bearerToken.pojo.Login;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginTest extends BaseBearerTokenApiTest {
    @Test
    public void loginShouldSuccess(){
        given()
//                .port(80)
                .header("Content-Type","application/json")
                .body(new Login("superman@gmail.com","123456"))
                .log().uri()
                .when()
                .post("https://apingweb.com/api/login")
                .then()
                .statusCode(200)
                //.body("message",equalTo("LoginSuccess"))
                .log().body();
    }
}
