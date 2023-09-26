package com.jsonServer.api.test.jsonServer;

import com.jsonServer.api.test.jsonServer.pojo.Post;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class WriteApiTest extends BaseJsonServerApiTest {
    @Test
    public void createPostShouldSuccess(){
        String json = "{\n" +
                "  \"title\": \"json-server2\",\n" +
                "  \"author\": \"typicode2\"\n" +
                "}";
        given()
                .port(3000)
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
                .port(3000)
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
                .port(3000)
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
                .port(3000)
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
                .body("id",notNullValue())
                .extract().jsonPath().getObject("", Post.class);
    }
    @Test
    public void createPostWithPojoShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();


          Post post =    given()
                .port(3000)
                .header("Content-Type","application/json")
                .body(new Post(titleName,AuthorName))
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
                .extract().jsonPath().getObject("", Post.class);

        System.out.println("...........................................");
        System.out.println(post.getId());
        System.out.println(post.getTitle());
        System.out.println(post.getAuthor());
    }
    @Test
    public void updatePostWithPojoShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();



        Long id =  given()
                .port(3000)
                .header("Content-Type","application/json")
                .body(new Post(titleName,AuthorName))
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
                .extract().jsonPath().getObject("", Post.class).getId();

        titleName = LoremIpsum.getInstance().getTitle(3);
        AuthorName = LoremIpsum.getInstance().getName();



        given()
                .port(3000)
                .header("Content-Type","application/json")
                .body(new Post(titleName,AuthorName))
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
    public void updatePostFromListShouldSuccess(){

     List<Post> postList =   given()
                .port(3000)
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
             .log().body()
             .extract().jsonPath().getList("", Post.class);

      long id = postList.get(0).getId();
      String  titleName = LoremIpsum.getInstance().getTitle(3);
       String AuthorName = LoremIpsum.getInstance().getName();



        given()
                .port(3000)
                .header("Content-Type","application/json")
                .body(new Post(titleName,AuthorName))
                .log().uri()
                .log().body()
                .when()
                .put("/posts/"+id)
                .then()
                .statusCode(200)
                .log().body()
                .body("title",equalTo(titleName))
                .body("author",equalTo(AuthorName))
                .body("id",notNullValue());
//        System.out.println("........................................");
//     for(Post post : postList){
//         System.out.println(post.getId());
//         System.out.println(post.getTitle());
//         System.out.println(post.getAuthor());
//         System.out.println("........................................");
//     }
    }
    @Test
    public void updatePostWithHashMapShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();

        HashMap<String,Object> jsHashMap = new HashMap<>();
        jsHashMap.put("title",titleName );
        jsHashMap.put("author",AuthorName);

      int id =  given()
              .port(3000)
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
                .port(3000)
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
                .port(3000)
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
                .port(3000)
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
                .port(3000)
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
                .port(3000)
                .log().uri()
                .when()
                .delete("/posts/"+id)
                .then()
                .statusCode(200)
                .log().body();



    }
    @Test
    public void deletePostFromPojoShouldSuccess(){
        String titleName = LoremIpsum.getInstance().getTitle(3);
        String AuthorName = LoremIpsum.getInstance().getName();



        int id =  given()
                .port(3000)
                .header("Content-Type","application/json")
                .body(new Post(titleName,AuthorName))
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
                .port(3000)
                .log().uri()
                .when()
                .delete("/posts/"+id)
                .then()
                .statusCode(200)
                .log().body();



    }

    @Test
    public void deletePostFromListShouldSuccess(){

        List<Post> postList =   given()
                .port(3000)
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getList("", Post.class);

        long id = postList.get(0).getId();
        given()
                .port(3000)
                .log().uri()
                .when()
                .delete("/posts"+id)
                .then()
                .statusCode(404)
                .log().body();



    }
}
