package tests;

import domain.localizaciones.Departamento;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.ubicaciones.Ubicacion;
import org.checkerframework.checker.units.qual.A;
import services.georef.ServicioGeoref;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PruebaAPI {
  public static void main(String[] args) throws IOException {
    ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

    Provincia misiones = servicioGeoref.provincia(54);

    misiones.getMunicipios().forEach(municipio -> {
      System.out.println(municipio.nombre);
    });

    misiones.getDepartamentos().forEach(municipio -> {
      System.out.println(municipio.nombre);
    });

  }

}
