package domain.usuarios;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Estandar implements Usuario {
    String nombre;
    String contrasenia;
    ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
    public Estandar(String nombre, String contrasenia) throws IOException {

        if(validadorDeContrasenia.EsValida(contrasenia))
        this.contrasenia = validadorDeContrasenia.Hashear(contrasenia);
        else throw new IllegalArgumentException("La contraseña no cumple con los estandares establecidos");

        this.nombre = nombre;
    }
}
