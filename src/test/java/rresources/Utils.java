package rresources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;



public class Utils {
    public static RequestSpecification request;

    public RequestSpecification requestSpecification() throws IOException {
        PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));

        if (request == null) {
            request = new RequestSpecBuilder().
                    setBaseUri(getGlobalValue("baseURL"))
                    .addQueryParam("key","")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
             return request;
        }
        return request;
    }


    public  static String getGlobalValue(String key) throws IOException {
        Properties properties= new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Eyad Yousef\\IdeaProjects\\SecondApiFramework\\src\\test\\java\\rresources\\global.properties");
        properties.load(fis);
        return properties.getProperty(key);
    }
    public  String getJsonPath(Response response , String key){
        String responses =response.asString();
        JsonPath js= new JsonPath(responses);
        return js.get(key).toString();

    }
}
