package services;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.incidentes.CreadorDeIncidentes;
import domain.incidentes.Incidente;
import domain.servicios.PrestacionDeServicio;
import repositorios.incidentes.RepoIncidentes;
import services.notificador.Notificador;

import java.util.*;

public class IncidentesService {

  private RepoIncidentes repoIncidentes;

  public IncidentesService(RepoIncidentes repoIncidentes){
    this.repoIncidentes = repoIncidentes;
  }

  public void darDeAltaIncidente(Miembro miembro, PrestacionDeServicio prestacion,String observaciones){

    List<Incidente> incidentes = CreadorDeIncidentes.darDeAltaIncidente(miembro,prestacion,observaciones);

    repoIncidentes.add(incidentes.toArray(new Incidente[0]));

    Notificador.generarNotificacion(miembro.getUsuario(), observaciones);
  }

  public void darDeBajaIncidentesDeLaPrestacion(Miembro miembro, PrestacionDeServicio prestacion){

    List<Incidente> incidentes = repoIncidentes.getByPrestacion(prestacion);

    List<Comunidad> comunidadesDelMiembroCerrador = miembro.comunidades();
    List<Miembro> miembrosInteresados = new ArrayList<>();
    comunidadesDelMiembroCerrador.forEach(comunidad -> {
      miembrosInteresados.addAll(comunidad.getMiembros());
    }
    );

    Set<Miembro> miembrosANotificar = new HashSet<>(miembrosInteresados);

    List<Incidente> incidentesDeLasComunidadesDelMiembro = incidentes.stream().filter(incidente -> comunidadesDelMiembroCerrador.contains(incidente.getComunidad())).toList();

    incidentesDeLasComunidadesDelMiembro.forEach(incidente -> incidente.cerrar(miembro));
    miembrosANotificar.forEach(miembroAInformar -> Notificador.generarNotificacion(miembroAInformar.getUsuario(), "Se cerro el incidente"));
  }
}
