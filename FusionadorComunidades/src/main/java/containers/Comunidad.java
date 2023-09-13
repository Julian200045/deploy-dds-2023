package containers;

import java.util.List;

public class Comunidad {
  Long id;
  Double gradoConfianza;
  List<Long> miembros;
  List<PrestacionDeServicio> prestacionesDeServicio;

  public Double getGradoConfianza() {
    return gradoConfianza;
  }

  public void setGradoConfianza(Double gradoConfianza) {
    this.gradoConfianza = gradoConfianza;
  }

  public List<Long> getMiembros() {
    return miembros;
  }

  public void setMiembros(List<Long> miembros) {
    this.miembros = miembros;
  }

  public List<PrestacionDeServicio> getPrestacionesDeServicio() {
    return prestacionesDeServicio;
  }

  public void setPrestacionesDeServicio(List<PrestacionDeServicio> prestacionesDeServicio) {
    this.prestacionesDeServicio = prestacionesDeServicio;
  }

  public Long getId() {
    return id;
  }
}
