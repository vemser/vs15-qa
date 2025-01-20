package com.vemser.rest.tests.login;

import com.vemser.rest.client.LoginClient;
import com.vemser.rest.utils.constants.LoginConstants;
import com.vemser.rest.data.factory.LoginDataFactory;
import com.vemser.rest.model.login.LoginRequest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class LoginTest {

    LoginClient loginClient = new LoginClient();

    @Test
    public void testDeveRealizarLoginComSucesso() {

        LoginRequest login = new LoginRequest();
        login.setEmail("valorValido");
        login.setPassword("valorValido");

        LoginRequest loginValido = LoginDataFactory.loginValido();

        loginClient.realizarLogin(loginValido)
                .then()
                    .assertThat()
                        .statusCode(HttpStatus.SC_OK)
                        .body(LoginConstants.MESSAGE, equalTo(LoginConstants.MSG_LOGIN_COM_SUCESSO))
                        .body(LoginConstants.AUTHORIZATION, notNullValue())
                ;
    }
}
