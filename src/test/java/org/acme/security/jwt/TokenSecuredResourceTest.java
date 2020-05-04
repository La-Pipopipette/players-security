package org.acme.security.jwt;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TokenSecuredResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/secured")
          .then()
             .statusCode(404);
    }

}