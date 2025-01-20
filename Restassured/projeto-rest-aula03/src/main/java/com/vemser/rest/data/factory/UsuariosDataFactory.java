package com.vemser.rest.data.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vemser.rest.client.UsuariosClient;
import com.vemser.rest.utils.constants.UsuariosConstants;
import com.vemser.rest.model.usuario.UsuariosRequest;
import com.vemser.rest.model.usuario.UsuariosResponse;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Random;

public class UsuariosDataFactory {

    private static UsuariosClient usuarioClient = new UsuariosClient();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Faker faker = new Faker(new Locale("PT-BR"));
    private static Random geradorBolean = new Random();

    public static UsuariosRequest usuarioValido() {
        return novoUsuario();
    }

    public static UsuariosRequest usuarioComNomeEmBranco() {
        UsuariosRequest usuarioResquest = novoUsuario();
        usuarioResquest.setNome(StringUtils.EMPTY);

        return usuarioResquest;
    }

    public static UsuariosRequest usuarioComEmailEmBranco() {
        UsuariosRequest usuarioResquest = novoUsuario();
        usuarioResquest.setEmail(StringUtils.EMPTY);

        return usuarioResquest;
    }

    public static UsuariosRequest usuarioComPasswordEmBranco() {
        UsuariosRequest usuarioResquest = novoUsuario();
        usuarioResquest.setPassword(StringUtils.EMPTY);

        return usuarioResquest;
    }

    public static UsuariosRequest usuarioComIsAdminEmBranco() {
        UsuariosRequest usuarioResquest = novoUsuario();
        usuarioResquest.setAdministrador(StringUtils.EMPTY);

        return usuarioResquest;
    }

    public static String cadastrarUsuarioERetornarID() {

        return usuarioClient.cadastrarUsuarios(novoUsuario())
                .path(UsuariosConstants.ID);
    }

    public static Object[] atualizarUsuarioComDadosValidos() {

        return new Object[] {novoUsuario(), cadastrarUsuarioERetornarID()};
    }

    public static Object[] atualizarUsuarioComEmailCadastrado() {

        UsuariosRequest usuarioResquest = novoUsuario();
        usuarioResquest.setEmail(retornarUsuarioExistente().getEmail());

        return new Object[] {usuarioResquest, cadastrarUsuarioERetornarID()};
    }

    public static UsuariosResponse retornarUsuarioExistente() {

        UsuariosResponse usuario =
                objectMapper.convertValue(usuarioClient.listarUsuarios().path("usuarios[0]"), UsuariosResponse.class);

        return usuario;
    }

    public static String retornarIDInvalido() {
        return faker.idNumber().invalid();
    }

    private static UsuariosRequest novoUsuario() {

        UsuariosRequest usuarioResquest = new UsuariosRequest();
        usuarioResquest.setNome(faker.name().fullName());
        usuarioResquest.setEmail(faker.internet().emailAddress());
        usuarioResquest.setPassword(faker.internet().password());
        usuarioResquest.setAdministrador(String.valueOf(geradorBolean.nextBoolean()));

        return usuarioResquest;
    }

}
