package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import lombok.Getter;

public class Localidad {
  public Long id;
  public String nombre;
  public Ubicacion ubicacion;
  @Getter
  private Municipio municipio;
  @Getter
  private Provincia provincia;

  public Localidad(Long id, String nombre, Ubicacion ubicacion, Municipio municipio, Provincia provincia) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.municipio = municipio;
    this.provincia = provincia;
  }

}
