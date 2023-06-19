package domain.entidades;

import domain.establecimientos.TipoEstablecimiento;
import lombok.Getter;

import java.util.List;

public class TipoEntidad {
  public String nombre;
  public String descripcion;
  @Getter
  private List<TipoEstablecimiento> tiposDeEstablecimientosPermitidos;

  public TipoEntidad(String nombre, String descripcion, List<TipoEstablecimiento> tiposDeEstablecimientosPermitidos) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.tiposDeEstablecimientosPermitidos = tiposDeEstablecimientosPermitidos;
  }
}
