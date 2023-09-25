package com.jsonServer.api.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetPostTest extends BaseTest{
    @Test
    public void getPostShouldSuccess(){
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body();
    }
    @Test
    public void getPostDetailShouldSuccess(){
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .log().body();
    }
}
