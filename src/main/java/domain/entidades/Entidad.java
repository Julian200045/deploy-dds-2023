package domain.entidades;

import domain.establecimientos.Establecimiento;
import domain.localizaciones.Localizacion;

import java.util.List;

public class Entidad {
  public String nombre;
  public List<Establecimiento> establecimientos;
  public TipoEntidad tipo;
  public Localizacion localizacion;
}
