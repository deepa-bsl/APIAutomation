package org.example;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class addPlace
{
    public static void main(String[]args)
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().queryParam("key","qaclick123").headers("Content-Type","application/json")
                .body("{\"location\": {\n" +
                        "\t\t\t\t   \"lat\": -38.383494,\n" +
                        "\t\t\t\t    \"lng\": 33.427362 \n" +
                        "\t\t\t\t  },  \n" +
                        "\t\t\t\t  \"accuracy \": 50, \n" +
                        "\t\t\t\t  \"name \":  \"Rahul Shetty Academy \", \n" +
                        "\t\t\t\t   \"phone_number \":  \"( 91) 983 893 3937 \",  \n" +
                        "\t\t\t\t   \"address \":  \"29, side layout, cohen 09 \", \n" +
                        "\t\t\t\t\"types \": [   \n" +
                        "\t\t\t\t  \"shoe park \",  \n" +
                        "\t\t\t\t    \"shop \"  \n" +
                        "\t\t\t\t  ],   \n" +
                        "\t\t\t\t   \"website \":  \"http://rahulshettyacademy.com \",  \n" +
                        "\t\t\t\t \"language \":  \"French-IN \" \n" +
                        "\t\t\t\t}").when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200);
    }
}
