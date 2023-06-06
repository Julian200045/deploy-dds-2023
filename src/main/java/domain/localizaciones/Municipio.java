package domain.localizaciones;

import domain.ubicaciones.Ubicacion;

public class Municipio implements Localizacion{
  public Integer id;
  public String nombre;
  public Ubicacion ubicacion;

  public Municipio(int id, String nombre, Ubicacion ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;
  }

  @Override
  public Boolean seEncuentraEn(Ubicacion ubicacion) {
    return ubicacion == this.ubicacion;
  }
}
