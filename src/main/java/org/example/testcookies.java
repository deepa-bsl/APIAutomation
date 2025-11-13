package org.example;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class testcookies {


    @Test(priority = 1)
    void testcookies() {
        given()
                .baseUri("https://google.com")
                .contentType("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .log().body()
                .log().headers();


    }

    @Test(priority = 2)
    void getcookiesinfo() {
        Response res = given()
                .baseUri("https://google.com")
                .contentType("application/json")
                .when()
                .get();

/*               String cookie_val1=res.getCookie("AEC");
               System.out.println("value of cookie is " +cookie_val1); */

        Map<String, String> cookies_values = res.getCookies();
        System.out.println(cookies_values.keySet());
        for (String k : cookies_values.keySet()) {
            String cookie_value = res.getCookie(k);
            System.out.println(k + "             " + cookie_value);

            System.out.println("The Header content-type value is " +res.getHeader("Content-Type"));

            Headers myheaders = res.getHeaders();

            for(Header hd:myheaders)
            {
              //  System.out.println("The value of the header  " +hd.getValue());
                System.out.println(hd.getName() +hd.getValue());
            }


        }
    }

    @Test(priority = 3)
    void testheaderinfo() {
       given()
                .baseUri("https://google.com")
                .contentType("application/json")
                .when()
                .get()
               .then()
               .header("Content-Type","text/html; charset=ISO-8859-1")
               .and()
               .header("Content-Encoding","gzip")
               .and()
               .header("Server","gws");




    }

    }
