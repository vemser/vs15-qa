package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.utils.constants.UsuariosConstants;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.usuario.UsuariosRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CadastrarUsuariosTest {

    private final UsuariosClient usuariosClient = new UsuariosClient();

    @Test
    public void testDeveCadastrarUsuarioComSucesso() {

        UsuariosRequest usuarioResquest = UsuariosDataFactory.usuarioValido();

        usuariosClient.cadastrarUsuarios(usuarioResquest)
                .then()
                    .assertThat()
                        .statusCode(201)
                        .body(UsuariosConstants.MESSAGE, equalTo(UsuariosConstants.MSG_CADASTRO_COM_SUCESSO))
                        .body(UsuariosConstants.ID, notNullValue())
                ;
    }

    @Test
    public void testTentarCadastrarUsuarioComEmailEmBranco() {

        UsuariosRequest usuarioResquestSemEmail = UsuariosDataFactory.usuarioComEmailEmBranco();

        usuariosClient.cadastrarUsuarios(usuarioResquestSemEmail)
                .then()
                    .assertThat()
                        .statusCode(400)
                        .body(UsuariosConstants.EMAIL, equalTo(UsuariosConstants.MSG_EMAIL_EM_BRANCO))
                ;
    }

    @ParameterizedTest
    @MethodSource("com.vemser.rest.data.provider.UsuariosDataProvider#usuarioDataProvider")
    public void testTentarCadastrarUsuarioComDadosInvalidos(UsuariosRequest usuarioResquest, String chave, String valor) {

        usuariosClient.cadastrarUsuarios(usuarioResquest)
                .then()
                    .assertThat()
                        .statusCode(400)
                        .body(chave, equalTo(valor))
                ;
    }
}
