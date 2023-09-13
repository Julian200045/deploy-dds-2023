package dtos;

import containers.Comunidad;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class RespuestaPropuestaFusion {
  String mensaje;
  List<List<Comunidad>> propuestas;

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public List<List<Comunidad>> getPropuestas() {
    return propuestas;
  }

  public void setPropuestas(List<List<Comunidad>> propuestas) {
    this.propuestas = propuestas;
  }
}
