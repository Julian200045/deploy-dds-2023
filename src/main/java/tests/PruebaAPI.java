package tests;

import services.georef.ServicioGeoref;
import services.georef.entities.*;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class PruebaAPI {
  public static void main(String[] args) throws IOException {
    ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
    System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

    ListaProvinciasEntity listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias();

    listadoDeProvinciasArgentinas.provincias.sort((p1, p2) -> p1.id >= p2.id? 1 : -1);

    for(ProvinciaEntity unaProvincia:listadoDeProvinciasArgentinas.provincias){
      System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
    }

    Scanner entradaEscaner = new Scanner(System.in);
    int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());

    Optional<ProvinciaEntity> posibleProvincia = listadoDeProvinciasArgentinas.provinciaDeId(idProvinciaElegida);

    if(posibleProvincia.isPresent()){
      ProvinciaEntity provinciaSeleccionada = posibleProvincia.get();
      ListaMunicipiosEntity municipiosDeLaProvincia = servicioGeoref.listadoDeMunicipiosDeProvincia(provinciaSeleccionada);
      System.out.println("Los municipios de la provincia "+ provinciaSeleccionada.nombre +" son:");
      for(MunicipioEntity unMunicipio: municipiosDeLaProvincia.municipios){
        System.out.println(unMunicipio.nombre);
      }
    }
    else{
      System.out.println("No existe la provincia seleccionada");
    }
  }

}
