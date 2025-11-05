package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class createairline {
    public static void main(String[]args){
  String endPoint="https://restful-booker.herokuapp.com/booking";
  String payload="{\n" +
          "    \"firstname\" : \"Jim\",\n" +
          "    \"lastname\" : \"Brown\",\n" +
          "    \"totalprice\" : 111,\n" +
          "    \"depositpaid\" : true,\n" +
          "    \"bookingdates\" : {\n" +
          "        \"checkin\" : \"2018-01-01\",\n" +
          "        \"checkout\" : \"2019-01-01\"\n" +
          "    },\n" +
          "    \"additionalneeds\" : \"Breakfast\"\n" +
          "}";
       Response response=RestUtils.performPost(endPoint,payload,new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);


        System.out.println("Printing get response");
        Response response1= RestAssured.given().log().all()
                .baseUri("https://restful-booker.herokuapp.com/booking/1078")
                .contentType(ContentType.JSON)
                .get()
                .then().log().all().extract().response();

        Assert.assertEquals(response1.statusCode(),200);

    }
}
