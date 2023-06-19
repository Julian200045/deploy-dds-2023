package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import java.util.List;
import lombok.Getter;


public class Provincia {
  public Integer id;
  public String nombre;
  public Ubicacion ubicacion;
  @Getter
  private final List<Municipio> municipios;
  @Getter
  private final List<Localidad> localidades;

  public Provincia(Integer id, String nombre, Ubicacion ubicacion, List<Municipio> municipios, List<Localidad> localidades) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.municipios = municipios;
    this.localidades = localidades;
  }
}
