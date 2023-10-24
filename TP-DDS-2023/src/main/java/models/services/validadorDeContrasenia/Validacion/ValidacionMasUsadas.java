package models.services.validadorDeContrasenia.Validacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.services.LectorPropiedades;

public class ValidacionMasUsadas implements Validacion {

  private static List<String> constraseniasInvalidas;
  private LectorPropiedades lectorPropiedades;

  public ValidacionMasUsadas(LectorPropiedades lectorPropiedades) {
    this.lectorPropiedades = lectorPropiedades;
    if (constraseniasInvalidas == null) {
      try {
        constraseniasInvalidas = cargarContraseniasMasUsadas(lectorPropiedades.getPropiedad("password-top-10000-path"));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

  }

  private List<String> cargarContraseniasMasUsadas(String path) throws FileNotFoundException {
    File contraseniasInvalidas = new File(path);
    Scanner scaner = new Scanner(contraseniasInvalidas);

    List<String> contraseniasMasUsadas = new ArrayList<>();

    while (scaner.hasNextLine()) {
      String data = scaner.nextLine();
      contraseniasMasUsadas.add(data);
    }

    return contraseniasMasUsadas;
  }

  @Override
  public Boolean valida(String contrasenia) {
    return !constraseniasInvalidas.contains(contrasenia);
  }

}
