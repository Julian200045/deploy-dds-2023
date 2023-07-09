package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import lombok.Getter;

import java.util.List;

public class Municipio{
  public Integer id;
  public String nombre;
  public Ubicacion ubicacion;
  @Getter
  private Provincia provincia;
  @Getter
  private List<Localidad> localidades;

  public Municipio(int id, String nombre, Ubicacion ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;

  }

  public void setProvincia(Provincia provinciaASetear){
    if(provincia == null){
      provincia = provinciaASetear;
    }
  }

  public void setLocalidades(List<Localidad> localidadesList){
    if(localidades == null){
      localidades = localidadesList;
    }
  }
}
