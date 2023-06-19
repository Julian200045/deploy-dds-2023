package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import lombok.Getter;

import java.util.List;

public class Municipio{
  public Integer id;
  public String nombre;
  public Ubicacion ubicacion;

  public Provincia provincia;
  @Getter
  private final List<Localidad> localidades;

  public Municipio(int id, String nombre, Ubicacion ubicacion,Provincia provincia,List<Localidad> localidades) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
    this.provincia = provincia;
    this.localidades = localidades;
  }

}
