package domain.establecimientos;

import domain.localizaciones.Localidad;
import domain.localizaciones.Municipio;
import domain.localizaciones.Provincia;
import domain.servicios.PrestacionDeServicio;
import domain.ubicaciones.Ubicacion;
import lombok.Getter;

import java.util.List;

public class Establecimiento {
  private String nombre;
  private Ubicacion ubicacion;
  private List<PrestacionDeServicio> prestaciones;
  @Getter
  public Localidad localidad;

  public Establecimiento(String nombre, Ubicacion ubicacion) {
    this.nombre = nombre;
    this.ubicacion = ubicacion;
  }

  public void agregarPrestacionDeServicio(PrestacionDeServicio prestacion) {
    prestaciones.add(prestacion);
  }

  Municipio getMunicipioDeInteres(){
    return localidad.getMunicipio();
  }
  Provincia getProvinciaDeInteres(){
    return localidad.getProvincia();
  }
}
