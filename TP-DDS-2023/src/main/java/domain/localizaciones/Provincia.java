package domain.localizaciones;

import domain.ubicaciones.Ubicacion;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


public class Provincia {
  public Integer id;
  public String nombre;
  public Ubicacion ubicacion;
  @Getter
  private List<Municipio> municipios;


  public Provincia(Integer id, String nombre, Ubicacion ubicacion) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = ubicacion;

  }

  public List<Localidad> getLocalidades(){
    return municipios.stream().flatMap(municipio -> municipio.getLocalidades().stream()).toList();
  }

  public void setMunicipios(List<Municipio> municipiosList){
    if(this.municipios == null){
      municipios = municipiosList;
    }
  }
}
