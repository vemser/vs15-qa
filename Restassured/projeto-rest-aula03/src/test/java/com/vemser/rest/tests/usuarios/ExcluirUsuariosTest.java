package com.vemser.rest.tests.usuarios;

import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.utils.constants.UsuariosConstants;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class ExcluirUsuariosTest {

    private UsuariosClient usuarioClient = new UsuariosClient();

    @Test
    public void testDeveExcluirUsuarioComSucesso() {

        usuarioClient.excluirUsuarios(UsuariosDataFactory.cadastrarUsuarioERetornarID())
                .then()
                    .statusCode(200)
                    .body(UsuariosConstants.MESSAGE, equalTo(UsuariosConstants.MSG_EXCLUIR_COM_SUCESSO))
        ;
    }

    @Test
    public void testTentarExcluirUsuarioComIDInvalido() {

        usuarioClient.excluirUsuarios(UsuariosDataFactory.retornarIDInvalido())
                .then()
                    .statusCode(200)
                    .body(UsuariosConstants.MESSAGE, equalTo(UsuariosConstants.MSG_EXCLUIR_ID_INVALIDO))
                ;
    }
}
