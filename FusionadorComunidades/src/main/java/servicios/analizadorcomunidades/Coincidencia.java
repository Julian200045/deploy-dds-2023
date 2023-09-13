package servicios.analizadorcomunidades;

import containers.Comunidad;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

class Coincidencia {
  Comunidad comunidad;
  List<Coincidencia> coincidencias = new ArrayList<>();

  public Coincidencia(Comunidad comunidad) {
    this.comunidad = comunidad;
  }

  public Boolean esValida() {
    return coincidencias.stream().allMatch(c -> new HashSet<>(c.coincidencias).containsAll(coincidencias));
  }

  public List<Coincidencia> getCoincidencias() {
    return coincidencias;
  }

  public void setCoincidencias(List<Coincidencia> coincidencias) {
    this.coincidencias = coincidencias;
  }

  public Comunidad getComunidad() {
    return comunidad;
  }
}