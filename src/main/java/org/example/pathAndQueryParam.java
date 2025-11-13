package org.example;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class pathAndQueryParam {

    @Test
    void testQueryAndPath()
    {
        given()
                .pathParams("mypath","users")
                .queryParam("page",5)
                .queryParam("id",2)
                .header("x-api-key", "reqres-free-v1")
        .when()
                .get("https://reqres.in/api/{mypath}")
         .then()
                .statusCode(200)
                .log().all();
    }
}
