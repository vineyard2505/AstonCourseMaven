
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class PostmanEchoTest {
    public static final String BASE_URL = "https://postman-echo.com";

    @Test
    public void postRawTextTest(){
        String requestBody = "Hello, world!";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .extract().response();
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
    }

    @Test
    public void testGetRequest() {
        Response response = given()
                .when()
                .get(BASE_URL + "/get")
                .then()
                .statusCode(200)
                .extract().response();
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
    }

    @Test
    public void postFromDataTest() {
        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post(BASE_URL + "/post")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(response.asString());
    }

    @Test
    public void testPutRequest() {
        Response response = given()
                .header("Content-Type", "application/json")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put(BASE_URL + "/put")
                .then()
                .statusCode(200)
                .extract().response();
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
    }

    @Test
    public void testDeleteRequest() {
        Response response = given()
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete(BASE_URL + "/delete")
                .then()
                .statusCode(200)
                .extract().response();
        String jsonResponse = response.asString();
        System.out.println(jsonResponse);
    }
}
