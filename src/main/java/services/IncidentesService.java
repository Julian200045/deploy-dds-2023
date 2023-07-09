package services;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.incidentes.CreadorDeIncidentes;
import domain.incidentes.Incidente;
import domain.servicios.PrestacionDeServicio;
import repositorios.incidentes.RepoIncidentes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncidentesService {

  private RepoIncidentes repoIncidentes;

  public IncidentesService(RepoIncidentes repoIncidentes){
    this.repoIncidentes = repoIncidentes;
  }

  public void darDeAltaIncidente(Miembro miembro, PrestacionDeServicio prestacion,String observaciones){

    List<Incidente> incidentes = CreadorDeIncidentes.darDeAltaIncidente(miembro,prestacion,observaciones);

    repoIncidentes.add(incidentes.toArray(new Incidente[0]));
  }

  public void darDeBajaIncidentesDeLaPrestacion(Miembro miembro, PrestacionDeServicio prestacion){

    List<Incidente> incidentes = repoIncidentes.getByPrestacion(prestacion);

    List<Comunidad> comunidadesDelMiembroCerrador = miembro.comunidades();

    List<Incidente> incidentesDeLasComunidadesDelMiembro = incidentes.stream().filter(incidente -> comunidadesDelMiembroCerrador.contains(incidente.getComunidad())).toList();

    incidentesDeLasComunidadesDelMiembro.forEach(incidente -> incidente.cerrar(miembro));
  }
}
