package restAssuredSimplePackage;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.core.Is.is;
//import static org.hamcrest.CoreMatchers.*;

public class RestAssuredSimpleClass {
    @Test
//For get request
    public void runRestGetTest() {
        given().//contentType(ContentType.JSON).log().all().
                when().get("https://jsonplaceholder.typicode.com/comments/2").
                then().log().all().statusCode(200).
                body("name", is("quo vero reiciendis velit similique earum")).
                body("email", is("Jayne_Kuhic@sydney.com")).
                body("body", is("est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et")).log().all();
    }

    @Test
    //For Post request
    public void runPostTest() {
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("userId", "10002");
        postBody.put("title", "My latest holiday");
        postBody.put("body", "I enjoyed my holiday. Really had a good time");


        given().contentType(ContentType.JSON).log().all().
                with().body(postBody).
                when().post("https://jsonplaceholder.typicode.com/posts").
                then().log().all().statusCode(201).
                body("title", is("My latest holiday"));
    }
}
