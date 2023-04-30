package services.ValidadorDeContrasenia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.common.hash.Hashing;
import services.LectorPropiedades;
import services.ValidadorDeContrasenia.Validacion.Validacion;

public class ValidadorDeContraseniasPorValidaciones {
    private static List<Validacion> validaciones;

    public ValidadorDeContraseniasPorValidaciones() throws IOException {
        validaciones = new ArrayList<>();

    }

    public Boolean esValida(String contrasenia) throws FileNotFoundException {
        return validaciones.stream().allMatch(validacion -> validacion.valida(contrasenia));
    }

    public void agregarValidacion(Validacion validacion){
        validaciones.add(validacion);
    }
}
