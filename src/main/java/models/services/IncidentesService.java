package models.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.entities.comunidades.Comunidad;
import models.entities.comunidades.Miembro;
import models.entities.comunidades.Persona;
import models.entities.incidentes.CreadorDeIncidentes;
import models.entities.incidentes.Incidente;
import models.entities.servicios.PrestacionDeServicio;
import models.repositorios.RepositorioIncidentes;
import models.services.notificador.GeneradorNotificaciones;
import org.quartz.SchedulerException;

public class IncidentesService {

  private RepositorioIncidentes repoIncidentes;
  private GeneradorNotificaciones servicioGeneradorNotificaciones;

  public IncidentesService(RepositorioIncidentes repoIncidentes, GeneradorNotificaciones servicioGeneradorNotificaciones) {
    this.repoIncidentes = repoIncidentes;
    this.servicioGeneradorNotificaciones = servicioGeneradorNotificaciones;
  }

  public void darDeAltaIncidente(Persona persona, PrestacionDeServicio prestacion, String observaciones) throws SchedulerException {

    List<Incidente> incidentes = CreadorDeIncidentes.darDeAltaIncidente(persona, prestacion, observaciones);
    repoIncidentes.guardar(incidentes.toArray());
    Set<Persona> miembrosANotificar = new HashSet<>();

    incidentes.forEach(incidente -> {
      miembrosANotificar.addAll(miembrosInteresados(incidente));
    });

    miembrosANotificar.forEach(miembroANotificar -> {
      servicioGeneradorNotificaciones.generarNotificacion(miembroANotificar.getUsuario(), "Se abrio el incidente: Descripcion" + observaciones);
    });

  }

  public void darDeBajaIncidentesDeLaPrestacion(Persona persona, PrestacionDeServicio prestacion) {

    List<Incidente> incidentes = repoIncidentes.buscarPorPrestacion(prestacion);

    List<Comunidad> comunidadesDelMiembroCerrador = persona.getComunidades();

    List<Incidente> incidentesDeLasComunidadesDelMiembro = incidentes.stream().filter(incidente -> comunidadesDelMiembroCerrador.stream().map(comunidad -> comunidad.getId()).toList().contains(incidente.getComunidad().getId())).toList();
    incidentesDeLasComunidadesDelMiembro.forEach(incidente -> {
      incidente.cerrar(persona.getMembresiaDeComunidad(incidente.getComunidad()));
      repoIncidentes.actualizar(incidente);
    });
    //repoIncidentes.limpiarCacheIncidentes();

    Set<Persona> miembrosANotificar = new HashSet<>();

    incidentes.forEach(incidente -> {
      miembrosANotificar.addAll(miembrosInteresados(incidente));
    });

    miembrosANotificar.forEach(miembroANotificar -> {
      servicioGeneradorNotificaciones.generarNotificacion(miembroANotificar.getUsuario(), "Se cerro el incidente");
    });

  }

  List<Persona> miembrosInteresados(Incidente incidente) {

    List<Comunidad> comunidadesDelMiembroCerrador = incidente.getMiembroApertura().getPersona().getMembresias().stream().map(miembro -> miembro.getComunidad()).toList();

    List<Persona> listaMiembrosInteresados = new ArrayList<>();
    comunidadesDelMiembroCerrador.forEach(comunidad -> {
          listaMiembrosInteresados.addAll(comunidad.getMiembros().stream().map(miembro -> miembro.getPersona()).toList());
        }
    );

    return listaMiembrosInteresados;
  }
}
