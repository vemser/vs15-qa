package com.vemser.rest.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class OlaMundoTest {

    @Test
    public void testBuscarUsuarioPorIDComSucesso(){

        baseURI = "https://reqres.in";

        given()
                .log().all()
        .when()
                .get("/api/users/2")
        .then()
                .log().all()
                .statusCode(200)
        ;

    }

}
