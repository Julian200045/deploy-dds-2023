package domain.incidentes;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.comunidades.Persona;
import domain.servicios.PrestacionDeServicio;

import java.util.List;

public class CreadorDeIncidentes {
  public static List<Incidente> darDeAltaIncidente(Miembro miembro, PrestacionDeServicio prestacion, String observaciones){

    List<Comunidad> comunidadesInteresadas = miembro.getPersona().comunidades().stream().filter(comunidad -> comunidad.prestacionEsDeInteres(prestacion)).toList();

    return comunidadesInteresadas.stream().map(comunidad -> new Incidente(prestacion,comunidad,observaciones,miembro)).toList();
  }
}
