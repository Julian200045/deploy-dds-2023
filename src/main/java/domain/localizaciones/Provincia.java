package domain.localizaciones;

import domain.ubicaciones.Ubicacion;

import java.util.List;

public class Provincia implements Localizacion{
  public int id;
  public String nombre;
  public Ubicacion ubicacion;
  private List<Municipio> municipios;
  private List<Departamento> departamentos;
}
