package domain.comunidades;

import domain.servicios.PrestacionDeServicio;
import java.util.ArrayList;
import java.util.List;

public class Comunidad {
  private String nombre;
  private final List<Miembro> miembros = new ArrayList<>();
  private final List<Miembro> administradores = new ArrayList<>();
  private final List<PrestacionDeServicio> prestacionDeServicios = new ArrayList<>();

  public Comunidad(String nombre) {
    this.nombre = nombre;
  }
}