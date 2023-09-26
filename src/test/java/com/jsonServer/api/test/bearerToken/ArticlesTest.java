package com.jsonServer.api.test.bearerToken;

import com.jsonServer.api.test.bearerToken.pojo.Login;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ArticlesTest extends BaseBearerTokenApiTest{
    @Test
    public void getArticleShouldSuccess(){
        given()
                .spec(getBearerTokenSpec())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .log().body();

    }


}
