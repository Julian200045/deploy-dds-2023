package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.localizaciones.Localizacion;
import java.util.List;
import lombok.Getter;

public class Entidad {
  @Getter
  public int id;
  public String nombre;
  public List<Establecimiento> establecimientos;
  public TipoEntidad tipo;
  public Localizacion localizacion;

  public Entidad(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }
}
