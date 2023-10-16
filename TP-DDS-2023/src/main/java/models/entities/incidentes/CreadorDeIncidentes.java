package models.entities.incidentes;

import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Miembro;
import models.entities.comunidades.Persona;
import models.entities.servicios.PrestacionDeServicio;

import java.util.List;

public class CreadorDeIncidentes {
  public static List<Incidente> darDeAltaIncidente(Miembro miembro, PrestacionDeServicio prestacion, String observaciones){

    List<Comunidad> comunidadesInteresadas = miembro.getPersona().comunidades().stream().filter(comunidad -> comunidad.prestacionEsDeInteres(prestacion)).toList();

    return comunidadesInteresadas.stream().map(comunidad -> new Incidente(prestacion,comunidad,observaciones,miembro)).toList();
  }
}
