package containers;

import java.util.List;

public class  Propuesta {
  List<Comunidad> comunidades;

  public Propuesta() {

  }

  public Propuesta(List<Comunidad> comunidades){
    this.comunidades = comunidades;
  }

  public List<Comunidad> getComunidades() {
    return comunidades;
  }

  public void setComunidades(List<Comunidad> comunidades) {
    this.comunidades = comunidades;
  }
}
