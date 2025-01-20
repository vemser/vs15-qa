package com.vemser.rest.client;

import com.vemser.rest.utils.constants.LoginConstants;
import com.vemser.rest.model.login.LoginRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    public Response realizarLogin(LoginRequest loginRequest) {

        return
            given()
                    .spec(set())
                    .body(loginRequest)
            .when()
                    .post(LoginConstants.ENDPOINT_LOGIN)
            ;
    }
}
