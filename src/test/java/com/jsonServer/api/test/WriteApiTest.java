package com.jsonServer.api.test;

import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class WriteApiTest extends BaseTest{
    @Test
    public void createPostShouldSuccess(){
        String json = "{\n" +
                "  \"title\": \"json-server2\",\n" +
                "  \"author\": \"typicode2\"\n" +
                "}";
        given()
                .header("Content-Type","application/json")
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body();
    }
    @Test
    public void createPostWithHashmapShouldSuccess(){
        HashMap<String,Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title", LoremIpsum.getInstance().getTitle(3));
        jsHashMap.put("author",LoremIpsum.getInstance().getName());

        given()

                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body();
    }
    @Test
    public void createPostWithHashmapAssertionShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();
        HashMap<String,Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title",titleName );
        jsHashMap.put("author",AuthorName);

        given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue());


    }
    @Test
    public void createPostWithJsonShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",titleName);
        jsonObject.put("author",AuthorName);

        given()
                .header("Content-Type","application/json")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue());
    }
    @Test
    public void updatePostWithHashMapShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();

        HashMap<String,Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title",titleName );
        jsHashMap.put("author",AuthorName);

      int id =  given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue())
                .extract().jsonPath().getInt("id");

        titleName = LoremIpsum.getInstance().getTitle(3);
        AuthorName = LoremIpsum.getInstance().getName();

        HashMap<String,Object> jsHashMap2 = new HashMap<>();
        jsHashMap2.put("title",titleName );
        jsHashMap2.put("author",AuthorName);

        given()
                .header("Content-Type","application/json")
                .body(jsHashMap2)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/"+id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue())
                .extract().jsonPath().getInt("id");
    }
    @Test
    public void updateSingleFillPostWithHashMapShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();

        HashMap<String,Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title",titleName );
        jsHashMap.put("author",AuthorName);

        int id =  given()
                .header("Content-Type","application/json")
                .body(jsHashMap)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue())
                .extract().jsonPath().getInt("id");


        AuthorName = LoremIpsum.getInstance().getName();

        HashMap<String,Object> jsHashMap3 = new HashMap<>();

        jsHashMap3.put("author",AuthorName);

        given()
                .header("Content-Type","application/json")
                .body(jsHashMap3)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/"+id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue())
                .extract().jsonPath().getInt("id");
    }
    @Test
    public void deletePostShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();

        HashMap<String,Object> jsHashMap4 = new HashMap<>();
        jsHashMap4.put("title",titleName);
        jsHashMap4.put("author",AuthorName);

        int id =  given()
                .header("Content-Type","application/json")
                .body(jsHashMap4)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue())
                .extract().jsonPath().getInt("id");

        given()
                .log().uri()
                .when()
                .delete("/posts/"+id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue());


    }
}
