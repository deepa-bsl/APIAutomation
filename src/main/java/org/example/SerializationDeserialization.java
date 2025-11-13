package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
// serialization we use jackson package and object mapper class

//JSoN is light weight and secure than Java object hence we use serialisation is used to send data over the internet
//serialisation and deserialisation automatically happens in rest assured we don't need to do it
//hash map is an interface and pojo are all java objects


public class SerializationDeserialization {
//serialise convert pojo to json

@Test
    void convertPojo2Json() throws JsonProcessingException {

    //created  java object using pojo class
  Student stupojo= new Student(); //pojo
    stupojo.setName("Deepa");
    stupojo.setAge(25);
    stupojo.setLocation("India");
    String courseArr[]={"AML","GenAI","PlayWright"};
    stupojo.setCourses(courseArr);
//convert java object  -> json object(serialisation)

    ObjectMapper objMapper =new ObjectMapper();
    String jsondata =objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
    System.out.println(jsondata);
}


@Test
    void convertJson2Pojo() throws JsonProcessingException {
    String jsondata= "{\n" +
            "  \"name\" : \"Deepa\",\n" +
            "  \"location\" : \"India\",\n" +
            "  \"age\" : 25,\n" +
            "  \"courses\" : [ \"AML\", \"GenAI\", \"PlayWright\" ]\n" +
            "}\n";

    ObjectMapper objMapper= new ObjectMapper();
   Student stupojo= objMapper.readValue(jsondata, Student.class); //convert json to pojo- we also specify which format we need to create in student format so we specify student.class
    System.out.println("Name:"+stupojo.getName());
    System.out.println("location:"+stupojo.getLocation());
    System.out.println("Age:"+stupojo.getAge());
    System.out.println("Course1:"+stupojo.getCourses() [0]);

}
}
