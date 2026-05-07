package com.krce;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FakeApitest {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://api.escuelajs.co/api/v1";
    }
    @Test
    public void TestGetProducts(){
        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }
    @Test
    public void testfilterProductsByPrice(){
        RestAssured.given()
                .queryParam("price",100)
                .when()
                .get("/products/")
                .then()
                .statusCode(200)
                .body("[0].price",Matchers.equalTo(100));
    }
    @Test
    public void testGetCategories(){
        RestAssured.given()
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("$",Matchers.instanceOf(List.class));
    }
    @Test
    public void testGetCategoriesById(){

        RestAssured.given()
                .pathParam("id",1)
                .when()
                .get("/categories/{id}")
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(1));
    }
    public void testgetCategories(){
        RestAssured.given()
                .when()
                .get("/categories")
                .then()
                .statusCode(200)
                .body("$",Matchers.instanceOf(List.class));
    }


}
