package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.utils.constants.UsuariosConstants;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import com.vemser.rest.model.usuario.UsuariosRequest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class AtualizarUsuariosTest {

    private static UsuariosClient usuarioClient = new UsuariosClient();

    @Test
    public void testDeveAtualizarUsuarioComSucesso() {

        Object[] usuarioAtualizado = UsuariosDataFactory.atualizarUsuarioComDadosValidos();

        usuarioClient.atualizarUsuario((UsuariosRequest) usuarioAtualizado[0], (String) usuarioAtualizado[1])
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body(UsuariosConstants.MESSAGE, equalTo(UsuariosConstants.MSG_ATUALIZAR_COM_SUCESSO))
                ;
    }

    @Test
    public void testTentarAtualizarUsuarioComEmailCadastrado() {

        Object[] usuarioAtualizado = UsuariosDataFactory.atualizarUsuarioComEmailCadastrado();

        usuarioClient.atualizarUsuario((UsuariosRequest) usuarioAtualizado[0], (String) usuarioAtualizado[1])
                .then()
                    .assertThat()
                        .statusCode(400)
                        .body(UsuariosConstants.MESSAGE, equalTo(UsuariosConstants.MSG_EMAIL_JA_CADASTRADO))
                ;
    }
}
