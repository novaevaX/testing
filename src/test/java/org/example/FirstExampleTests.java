package org.example;

import org.example.pojos.PostPojo;
import org.junit.Test;
import io.restassured.RestAssured;

import java.util.List;

public class FirstExampleTests {

    //проверяем соединение
    @Test
    public void openConnectedTest(){
        RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath)
        .then()
                .assertThat()
                .statusCode(200);
    }

    //получить все ресурсы - string
    @Test
    public void givenAllResourcesAsStringTest(){
        String res = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath)
        .then()
                .extract()
                .asString();
    }

    //получить все ресурсы - pojoClass
    @Test
    public void givenAllResourcesAsPojoClassTest(){
        List<PostPojo> postPojo = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath)
        .then()
                .extract()
                .jsonPath()
                .getList("$", PostPojo.class);
    }

    //получить ресурс по id - string
    @Test
    public void givenIdAsStringTest(){
        String res = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath + "/1")
        .then()
                .extract()
                .asString();
    }

    //получить ресурс по id - pojoClass
    @Test
    public void givenIdAsPojoClassTest(){
        PostPojo postPojo = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath + "/2")
        .then()
                .extract()
                .jsonPath()
                .getObject("", PostPojo.class);
    }

    //фильтрация по параметрам - userId - String
    @Test
    public void givenUserIdAsStringTest(){
        String res = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath + "?userId=1&id=7")
        .then()
                .extract()
                .asString();
    }

    //фильтрация по параметрам - userId - PojoClass
    @Test
    public void givenUserIdAsPojoClassTest(){
        List<PostPojo> postPojo = RestAssured
        .when()
                .get(Points.baseUrl + Points.basePath + "?userId=3")
        .then()
                .extract()
                .jsonPath()
                .getList("", PostPojo.class);
    }

    @Test
    public void getVoidListTest(){
        RestAssured
                .when()
                .get(Points.baseUrl + Points.basePath + "/0")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
