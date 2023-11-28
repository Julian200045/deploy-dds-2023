package models.services.validadorDeContrasenia.Validacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.services.LectorPropiedades;

public class ValidacionMasUsadas implements Validacion {

  private List<String> contraseniasInvalidas;
  private String pathContrasenias;

  public ValidacionMasUsadas(String pathContrasenias) {
    this.pathContrasenias = pathContrasenias;
    this.contraseniasInvalidas = this.cargarContraseniasMasUsadas(pathContrasenias);
  }

  private List<String> cargarContraseniasMasUsadas(String path) {
    File contraseniasInvalidas = new File(path);
    try {
      Scanner scaner = new Scanner(contraseniasInvalidas);
      List<String> contraseniasMasUsadas = new ArrayList<>();
      while (scaner.hasNextLine()) {
        String data = scaner.nextLine();
        contraseniasMasUsadas.add(data);
      }
      return contraseniasMasUsadas;

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Boolean valida(String contrasenia) {
    return !contraseniasInvalidas.contains(contrasenia);
  }

}
