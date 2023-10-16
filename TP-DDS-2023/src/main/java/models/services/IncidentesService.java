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

  public void darDeAltaIncidente(Miembro miembro, PrestacionDeServicio prestacion, String observaciones) throws SchedulerException {

    List<Incidente> incidentes = CreadorDeIncidentes.darDeAltaIncidente(miembro, prestacion, observaciones);
    repoIncidentes.guardar(incidentes.toArray());
    System.out.println("Guarde los incidentes");
    System.out.println(incidentes);
    Set<Persona> miembrosANotificar = new HashSet<>();

    incidentes.forEach(incidente -> {
      miembrosANotificar.addAll(miembrosInteresados(incidente));
    });

    miembrosANotificar.forEach(miembroANotificar -> {
      servicioGeneradorNotificaciones.generarNotificacion(miembroANotificar.getUsuario(), "Se abrio el incidente: Descripcion" + observaciones);
    });

  }

  public void darDeBajaIncidentesDeLaPrestacion(Miembro miembro, PrestacionDeServicio prestacion) {

    List<Incidente> incidentes = repoIncidentes.buscarPorPrestacion(prestacion);

    List<Comunidad> comunidadesDelMiembroCerrador = miembro.getPersona().comunidades();

    List<Incidente> incidentesDeLasComunidadesDelMiembro = incidentes.stream().filter(incidente -> comunidadesDelMiembroCerrador.contains(incidente.getComunidad())).toList();
    incidentesDeLasComunidadesDelMiembro.forEach(incidente -> {
      incidente.cerrar(miembro);
      repoIncidentes.actualizar(incidente);
    });

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
