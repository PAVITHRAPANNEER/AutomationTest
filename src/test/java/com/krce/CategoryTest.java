package com.krce;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class CategoryTest {
    private int id;
   @BeforeClass
    public void Setup(){
       RestAssured.baseURI="https://api.escuelajs.co/api/v1";
    }
    @Test(priority = 1)
    public void testCreateCategory(){
       String name= "user_"+System.currentTimeMillis();
       String image="https://google.com";
       Map body=Map.of("name",name,"image",image);
       Response response =RestAssured.given()
               .contentType(ContentType.JSON)
               .body(body)
               .when()
               .post("/categories");
       response
        .then()
                .log().all()
                .statusCode(201)
                .body("name", Matchers.equalTo(name));
       id=response.jsonPath().getInt("id");
    }
    @Test(priority = 2)
    public void testGetCategory(){
       RestAssured.given()
               .pathParam("id",id)
               .when()
                .get("/categories/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .body("id",Matchers.equalTo(id));
    }
    @Test(priority=3)
    public void TestUpdateCategory(){
       String name="category_"+System.currentTimeMillis();
       String image="https//google.com";
       RestAssured.given()
               .pathParam("id",id)
.body(       .when()
               .put("/categories/{id}")
               .then()
                .log().all()
                .statusCode(200)
                .body("id",Matchers.equalTo(name));
    }
}



