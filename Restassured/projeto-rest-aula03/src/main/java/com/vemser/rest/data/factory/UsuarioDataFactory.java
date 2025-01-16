package com.vemser.rest.data.factory;

import com.vemser.rest.model.Usuario;
import net.datafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class UsuarioDataFactory {

    static Faker faker = new Faker(new Locale("PT-BR"));
    static Random geradorBolean = new Random();

    public static Usuario usuarioValido() {
        return novoUsuario();
    }

    public static Usuario usuarioSemEmail() {
        Usuario usuario = novoUsuario();
        usuario.setEmail("");

        return usuario;
    }

    private static Usuario novoUsuario() {

        Usuario usuario = new Usuario();
        usuario.setNome(faker.name().fullName());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setPassword(faker.internet().password());
        usuario.setAdministrador(String.valueOf(geradorBolean.nextBoolean()));

        return usuario;
    }

}
