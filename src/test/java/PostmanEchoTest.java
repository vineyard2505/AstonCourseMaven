
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanEchoTest {
    public static final String BASE_URL = "https://postman-echo.com";

    @Test
    public void testGetRequest() {
        given()
                .when()
                .get(BASE_URL + "/get")
                .then()
                .statusCode(200)
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    public void postRawTextTest(){
        String requestBody = "Hello, world!";
        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void postFromDataTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(BASE_URL + "/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .patch(BASE_URL + "/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testDeleteRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        given()
                .body(requestBody)
                .when()
                .delete(BASE_URL + "/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }
}
