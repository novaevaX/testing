package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.example.pojos.PostPojo;
import org.junit.Test;
import io.restassured.RestAssured;

import java.util.List;

public class RestFirstTests {

    IdPosts idPosts = new IdPosts();

    //проверяем соединение
    @Test
    public void openResourcesTest(){
        RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath)
        .then()
                .assertThat()
                .statusCode(200);
    }

    //использование RestAssured
    @Test
    public void assuredGivenAllResourcesTest(){
        String res = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath)
        .then()
                .extract()
                .asString();

        System.out.println(res);
    }

    @Test
    public void getAllTest(){
        List<PostPojo> postPojoList = given()
                .baseUri(Points.baseUrl)
                .basePath(Points.basePath)
                .contentType(ContentType.JSON)
        .when()
                .get()
        .then()
                .extract()
                .jsonPath()
                .getList("post", PostPojo.class);
    }

    //получить все ресурсы - pojoClass
    @Test
    public void givenAllResourcesPojoTest(){
        List<PostPojo> postPojo = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath)
        .then()
                .extract()
                .jsonPath()
                .getList("$", PostPojo.class);
    }

    //получить ресурс по id (строка)
    @Test
    public void givenAllResourcesAsListTest(){
        String res = given()
                .baseUri(Points.baseUrl)
                .basePath(Points.basePath)
                .contentType(ContentType.JSON)
        .when()
                .get("/1")
        .then()
                .extract()
                .asString();

        System.out.println(res);
    }

    //фильтрация по параметрам - userId
    @Test
    public void givenUserIdTest(){
        String res = given()
                .baseUri(Points.baseUrl)
                .basePath(Points.basePath)
                .contentType(ContentType.JSON)
        .when()
                .get("?userId=1")
        .then()
                .extract()
                .asString();

        System.out.println(res);
    }

    //фильрация по параметрам - сравнение userId
    @Test
    public void equalsUserIdTest(){
        when()
                .get(Points.baseUrl + Points.basePath + "?id=11")
        .then()
                .body("userId", equalTo('2'));
    }
}
