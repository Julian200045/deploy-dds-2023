package domain.comunidades;

import domain.servicios.PrestacionDeServicio;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Comunidad {
  @Getter
  private String nombre;
  private final List<Miembro> miembros = new ArrayList<>();
  private final List<Miembro> administradores = new ArrayList<>();
  private final List<PrestacionDeServicio> prestacionDeServicios = new ArrayList<>();



  public Comunidad(String nombre) {
    this.nombre = nombre;
  }

  public void agregarPrestacionDeInteres(PrestacionDeServicio prestacionDeServicio){
    prestacionDeServicios.add(prestacionDeServicio);
  }

  public Boolean prestacionEsDeInteres(PrestacionDeServicio prestacionDeServicio){
    return prestacionDeServicios.contains(prestacionDeServicio);
  }

  public Boolean esMiembro(Miembro miembro){
    return miembros.contains(miembro);
  }

  public void agregarMiembro(Miembro miembro){
    miembros.add(miembro);
  }
}