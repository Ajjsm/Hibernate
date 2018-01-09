package com.juanaj.h2hibernate;

import com.juanaj.h2hibernate.entity.Usuarios;
import com.juanaj.h2hibernate.hibernate.Operations;

public class Main {
    private static Operations operations;

    public static void main(String[] args) {
        Usuarios usuarios = new Usuarios();
        usuarios.setNombre("Juan");
        usuarios.setApellidos("AJ");

        operations = new Operations();

        operations.conectar();
        operations.guardarUsuario(usuarios);
    }
}
