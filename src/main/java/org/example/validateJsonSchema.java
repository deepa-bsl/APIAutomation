package org.example;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class validateJsonSchema {

    @Test
    void validateJsonSchema()
    {
        given()
                .baseUri("http://localhost:3000/students")
                .when()
                .get()
                .then()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("studentSchema.json"));
    }
}
