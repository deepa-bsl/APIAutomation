package org.example;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class HTTPRequests
{
int id;
 @Test(priority=1)
 void getuser() {

     RestAssured.baseURI = "https://reqres.in/api/users?page=2";
     given()
             .header("x-api-key", "reqres-free-v1")
             .when()
             .get(baseURI)
             .then()
             .statusCode(200)
             .body("page", equalTo(2))
             .log().all();
 }
    @Test(priority=2)
    void createuser() {

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", "Deepa");
        data.put("job", "testlead");


        id = given()
                .baseUri("https://reqres.in/api/users")
                .contentType("application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(data)
                .when()
                .post(baseURI)
                .jsonPath().getInt("id");
    }

        @Test(priority=3,dependsOnMethods = {"createuser"})
        void updateuser() {

            HashMap<String, String> data = new HashMap<String, String>();
            data.put("name", "Ram");
            data.put("job", "VP");

            RestAssured.baseURI = "https://reqres.in/api/users/" + id;
            given()
                    .contentType("application/json")
                    .header("x-api-key", "reqres-free-v1")
                    .body(data)

                    .when()
                    .put(baseURI)
                    .then()
                    .statusCode(200)
                    .log().all();
        }

            @Test(priority=4)
             void deleteuser()
            {
                            baseURI="https://reqres.in/api/users/" + id;
                given()
                        .contentType("application/json")
                        .header("x-api-key", "reqres-free-v1")
                        .when()
                        .delete(baseURI)
                        .then()
                        .statusCode(204)
                        .log().all();


            }


    }



