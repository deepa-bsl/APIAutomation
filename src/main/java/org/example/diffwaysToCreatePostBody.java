package org.example;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class diffwaysToCreatePostBody {
int id;
    @Test(priority = 1)
    void testPostusingHashMap()
    {
        HashMap data=new HashMap();
        data.put("name","John");
        data.put("age",23);
        data.put("location","U.S");
        String courseArr[] ={"C","C++","Python"};
        data.put("courses",courseArr);

        given()
                .baseUri("http://localhost:3000")
                .contentType("application/json")
                .body(data)
        .when()
            .post("/students")

            .then()
                .statusCode(201)
                .body("name",equalTo("John"))
                .body("age",equalTo(23))
                .body("location",equalTo("U.S"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .body("courses[2]",equalTo("Python"))
                .header("Content-Type","application/json")
                .log().all();


    }

    @Test(priority = 2)
    void testPostusingJsonlibrary()
    {
       JSONObject data =new JSONObject();
        data.put("name","Mike");
        data.put("age",13);
        data.put("location","U.K");
        String courseArr[] ={"C#","Coding","Ruby"};
        data.put("courses",courseArr);

        Response response=given()
                .baseUri("http://localhost:3000")
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("Mike"))
                .body("age",equalTo(13))
                .body("location",equalTo("U.K"))
                .body("courses[0]",equalTo("C#"))
                .body("courses[1]",equalTo("Coding"))
                .body("courses[2]",equalTo("Ruby"))
                .header("Content-Type","application/json")
                .extract().response();

    }


    @Test(priority =3 )
    void testPostusingPOJO() {

        Pojo_PostRequest data= new Pojo_PostRequest();
        data.setName("Deepa");
        data.setAge(25);
        data.setLocation("India");
        String courseArr[]={"AML","GenAI","PlayWright"};
        data.setCourses(courseArr);
              given()
                .baseUri("http://localhost:3000")
                .contentType("application/json")
                .body(data)
              .when()
                 .post("/students")

              .then()
                .statusCode(201)
                .body("name", equalTo("Deepa"))
                .body("age",equalTo(25))
                .body("location",equalTo("India"))
                .body("courses[0]", equalTo("AML"))
                .body("courses[1]", equalTo("GenAI"))
                .body("courses[2]", equalTo("PlayWright"))
                .header("Content-Type", "application/json")
                      .log().all();



    }



    @Test(priority =4 )
    void testPostusingExternalJsonFile() throws FileNotFoundException {

      File f=new File(".\\body.json");
      FileReader fr= new FileReader(f);
      JSONTokener jt= new JSONTokener(fr);
      JSONObject data = new JSONObject(jt);
            given()
                .baseUri("http://localhost:3000")
                .contentType("application/json")
                .body(data.toString())
           .when()
                .post("/students")

           .then()
                .statusCode(201)
                .body("name", equalTo("June Seth"))
                .body("age",equalTo(27))
                .body("location",equalTo("russia"))
                .body("courses[0]", equalTo("BW"))
                .body("courses[1]", equalTo("HANA"))
                .body("courses[2]", equalTo("SAP"))
                .header("Content-Type", "application/json")
                .log().all();

    }



}
