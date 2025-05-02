package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LabseqSequenceControllerTest {
    @Test
    void testLabSeqEndpointInput1() {
        given()
          .when().get("/labseq/1")
          .then()
             .statusCode(200)
                .contentType("application/json")
                .body("result", equalTo("1"));
    }

    @Test
    void testLabSeqEndpointInput10() {
        given()
                .when().get("/labseq/10")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("result", equalTo("3"));
    }

    @Test
    public void testGetLabseqSequence_withInvalidIndex() {
        given()
                .when()
                .get("/labseq/-1")
                .then()
                .statusCode(400)
                .body("title", equalTo("Constraint Violation"))
                .body("status", equalTo(400))
                .body("violations.size()", equalTo(1))
                .body("violations[0].field", equalTo("getLabseqSequence.index"))
                .body("violations[0].message", equalTo("the index must be greater than or equal to 0"));
    }
    @Test
    void testLabSeqEndpointpassingLetter() {
        given()
                .when().get("/labseq/1a")
                .then()
                .statusCode(404)
                .contentType("application/json")
                .body("error", is("endpoint not found"));
    }

}