package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LabseqSequenceControllerTest {
    @Test
    void testLabSeqEndpoint() {
        given()
          .when().get("/labseq/1")
          .then()
             .statusCode(200)
                .contentType("application/json")
                .body("result", equalTo(1));
    }

    @Test
    void testLabSeqEndpointInput10() {
        given()
                .when().get("/labseq/10")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("result", equalTo(3));
    }

}