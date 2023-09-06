package analizadorComunidades;

import containers.Comunidad;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

class Coincidencia {
  @Getter
  Comunidad comunidad;
  @Getter
  @Setter
  List<Coincidencia> coincidencias = new ArrayList<>();

  public Coincidencia(Comunidad comunidad){
    this.comunidad = comunidad;
  }

  public Boolean esValida(){
    return coincidencias.stream().allMatch(c -> c.coincidencias.containsAll(coincidencias));
  }
}