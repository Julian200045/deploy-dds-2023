package services.ValidadorDeContrasenia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import services.ValidadorDeContrasenia.Validacion.Validacion;

public class ValidadorDeContraseniasPorValidaciones implements ValidadorDeContrasenias{
    private static List<Validacion> validaciones;

    public ValidadorDeContraseniasPorValidaciones() throws IOException {
        validaciones = new ArrayList<>();

    }

    public Boolean esValida(String contrasenia){
        return validaciones.stream().allMatch(validacion -> validacion.valida(contrasenia));
    }

    public void agregarValidacion(Validacion validacion){
        validaciones.add(validacion);
    }
}
