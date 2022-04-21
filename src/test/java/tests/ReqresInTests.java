package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class ReqresInTests {
    @Test
    void listUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12))
                .body("data.email[1]", is("lindsay.ferguson@reqres.in"));
    }
    @Test
    void singleUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
    @Test
    void singleUserNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }
    @Test
    void listResource() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .body("data.id[0]", is(1))
                .body("data.name[0]", is("cerulean"))
                .body("data.name[0]", is("cerulean"))
                .body("data.year[0]", is(2000))
                .body("data.color[0]", is("#98B2D1"))
                .body("data.pantone_value[0]", is("15-4020"));
    }
    @Test
    void singleResourceTest() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.name", is("fuchsia rose"))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}
