package tests;

import domain.localizaciones.Provincia;
import services.localizacionesService.LocalizacionesService;
import services.localizacionesService.georef.ServicioGeoref;

import java.io.IOException;

public class PruebaAPI {
  public static void main(String[] args) throws IOException {
    LocalizacionesService servicioGeoref = new ServicioGeoref("src/main/resources/template/project.properties");

    Provincia misiones = servicioGeoref.provincia(54);

    System.out.println("Provincia: "+ misiones.nombre + "\n");

    System.out.println("///////////////////////////////");
    System.out.println("Municipios de "+ misiones.nombre+ ":\n");

    misiones.getMunicipios().forEach(municipio -> {
      System.out.println(municipio.nombre);
    });

    System.out.println("///////////////////////////////");
    System.out.println("Departamentos de "+ misiones.nombre+ ":\n");

    misiones.getDepartamentos().forEach(departamento -> {
      System.out.println(departamento.nombre);
    });

  }

}
