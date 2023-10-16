package pruebasDeContexto;

import models.entities.localizaciones.Provincia;
import models.services.localizacionesService.LocalizacionesService;
import models.services.localizacionesService.georef.ServicioGeoref;

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
    System.out.println("localidades de "+ misiones.nombre+ ":\n");

    misiones.getLocalidades().forEach(localidad -> {
      System.out.println(localidad.nombre);
    });



  }

}
