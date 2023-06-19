package domain.entidades;

import domain.establecimientos.Establecimiento;
import java.util.List;
import java.util.stream.Collectors;

import domain.localizaciones.Localidad;
import lombok.Getter;

public class Entidad {
  @Getter
  public int id;
  public String nombre;
  public List<Establecimiento> establecimientos;
  public TipoEntidad tipo;

  public Entidad(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public List<Localidad> localidades(){
    return establecimientos.stream().map(establecimiento -> establecimiento.getLocalidad()).distinct().toList();
  }
}
