package dtos;

import containers.Comunidad;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RespuestaFusionComunidades {

  List<Comunidad> fusiones;

  public void setFusiones(List<Comunidad> fusiones) {
    this.fusiones = fusiones;
  }
  public List<Comunidad> getFusiones() {
    return fusiones;
  }
}