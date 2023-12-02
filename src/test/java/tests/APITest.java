package tests;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.Test;
import rresources.TestDataBuild;
import rresources.Utils;


import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class APITest extends Utils {

    RequestSpecification res;
    ResponseSpecification responspec;
    Response response;
    TestDataBuild testData = new TestDataBuild();


    @Test
    public void addPost() throws IOException {

        responspec = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON)
                .build();

        res = given()
                .spec(requestSpecification())
                .body(testData.addNewPost());


        response = res.
                when()
                .post("posts")
                .then().spec(responspec).extract().response();

        assertEquals(response.getStatusCode(), 201);
    }




    @Test
    public void getSpecificPost() throws IOException {

        responspec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON)
                .build();

        res = given()
                .spec(requestSpecification());


        response = res.
                when()
                .get("posts/10")
                .then().spec(responspec).extract().response();

        assertEquals(response.getStatusCode(), 200);

        String body = response.asString();
        JsonPath jsOpj= new JsonPath(body);
        assertEquals( "doloribus ad provident suscipit at", jsOpj.getString("title"));
    }


    @Test
    public void getAllPosts() throws IOException {

        responspec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON)
                .build();

        res = given()
                .spec(requestSpecification());


        response = res.
                when()
                .get("posts")
                .then().spec(responspec).extract().response();

        assertEquals(response.getStatusCode(), 200);

        String body = response.asString();

        JsonPath jsOpj= new JsonPath(body);
        System.out.println(" total number of users posts is "+ jsOpj.getList("id").size() + " user");

    }


}
