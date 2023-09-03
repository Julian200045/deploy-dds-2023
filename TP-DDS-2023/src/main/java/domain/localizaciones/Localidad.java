package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import lombok.Getter;

public class Localidad {
  public Long id;
  public String nombre;
  public Ubicacion ubicacion;
  @Getter
  private Municipio municipio;

  public Localidad(Long id, String nombre, Ubicacion ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
  }

  public void setMunicipio(Municipio municipioASetear){
    if(municipio == null){
      municipio = municipioASetear;
    }
  }
}
