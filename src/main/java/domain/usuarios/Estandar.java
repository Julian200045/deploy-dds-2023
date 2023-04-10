package domain.usuarios;

import java.io.FileNotFoundException;

public class Estandar implements Usuario {
    String nombre;
    String contrasenia;
    ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia(8,64);
    public Estandar(String nombre, String contrasenia) throws FileNotFoundException {

        if(validadorDeContrasenia.EsValida(contrasenia))
        this.contrasenia = validadorDeContrasenia.Hashear(contrasenia);
        else throw new IllegalArgumentException("La contraseña no cumple con los estandares establecidos");

        this.nombre = nombre;
    }
}
