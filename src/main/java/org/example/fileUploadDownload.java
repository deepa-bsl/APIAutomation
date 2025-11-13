package org.example;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class fileUploadDownload {


    @Test
    void fileupload() {
        File myfile = new File("C:\\Users\\mrm_r\\OneDrive\\Desktop\\abc.txt");
        given()
                .baseUri("https://api.escuelajs.co/api/v1/files/upload")
                .multiPart("file", myfile)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body("originalname", equalTo("abc.txt"))
                .log().all();
    }
@Test
void filedownload()
        {
           Response res= given()
                    .baseUri("https://api.escuelajs.co/api/v1/files/4fab.txt")
                    .when()
                    .get();

            String file_content=res.body().asString();
            Assert.assertEquals(res.statusCode(),200);
            Assert.assertEquals(file_content,"I love automation");


        }

    }

