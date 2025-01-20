package com.vemser.rest.data.provider;

import com.vemser.rest.utils.constants.UsuariosConstants;
import com.vemser.rest.data.factory.UsuariosDataFactory;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class UsuariosDataProvider {

    public static Stream<Arguments> usuarioDataProvider() {
        return Stream.of(
                Arguments.of(UsuariosDataFactory.usuarioComNomeEmBranco(), UsuariosConstants.NOME, UsuariosConstants.MSG_NOME_EM_BRANCO),
                Arguments.of(UsuariosDataFactory.usuarioComPasswordEmBranco(), UsuariosConstants.PASSWORD, UsuariosConstants.MSG_PASSWORD_EM_BRANCO),
                Arguments.of(UsuariosDataFactory.usuarioComIsAdminEmBranco(), UsuariosConstants.IS_ADMIN, UsuariosConstants.MSG_ISADMIN_EM_BRANCO)
        );
    }
}
