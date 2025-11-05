package org.example;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;
public class RestUtils {

    public static Response performPost(String endpoint, String requestPayload, Map<String,String>headers)
    {
        return RestAssured.given().log().all()
                .baseUri(endpoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .body(requestPayload).post()
                .then().log().all().extract().response();
    }
}
