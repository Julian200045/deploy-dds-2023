package dtos;

import containers.Comunidad;
import java.util.List;

import containers.Propuesta;

public class RespuestaPropuestaFusion {
  String mensaje;
  List<Propuesta> propuestas;

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public List<Propuesta> getPropuestas() {
    return propuestas;
  }

  public void setPropuestas(List<Propuesta> propuestas) {
    this.propuestas = propuestas;
  }
}
