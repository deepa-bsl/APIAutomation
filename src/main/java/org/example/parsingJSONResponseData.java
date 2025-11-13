package org.example;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class parsingJSONResponseData {
    @Test(priority = 1)
    void testJsonResponse() {
        given()
                .baseUri("http://localhost:3000")
                .contentType("application/json")
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("[1].location", equalTo("china"));
    }

    @Test(priority = 2)
    void testJsonResponse2() {
        Response res = given()
                .baseUri("http://localhost:3000")
                .when()
                .get("/students");
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/json");
        String location = res.jsonPath().get("[1].location").toString();
        Assert.assertEquals(location, "china");
    }

    @Test(priority = 3)
    void testJsonResponse3() {
        Response res = given()
                .baseUri("http://localhost:3000/students")
                .contentType(ContentType.JSON)
                .when()
                .get();
        res.then().statusCode(200);
        String jsonString = res.asString();
        JSONArray studentsArray = new JSONArray(jsonString);

        String targetName = "David Johnson";
        System.out.println("Student Information:");
        for (int i = 0; i < studentsArray.length(); i++) {
            JSONObject student = studentsArray.getJSONObject(i);
            String name = student.getString("name");
            String location = student.getString("location");

            System.out.println("Name: " + name + ", Location: " + location);

            // 4. Compare if the name is matching a value
            if (name.equals(targetName)) {
                System.out.println("Found student with name: " + targetName);
                // Optional: Add an assertion if this is part of a test
                Assert.assertEquals(name, targetName, "Student name does not match expected value.");
            }
        }
    }

}
