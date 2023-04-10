package domain.comunidades;

import domain.usuarios.Usuario;

public class Miembro {
    String nombre;
    String apellido;
    String email;
    Usuario usuario;

    public Miembro(String nombre, String apellido, String email, Usuario usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.usuario = usuario;
    }
}
