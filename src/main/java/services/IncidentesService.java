package services;

import domain.comunidades.Comunidad;
import domain.comunidades.Miembro;
import domain.incidentes.CreadorDeIncidentes;
import domain.incidentes.Incidente;
import domain.servicios.PrestacionDeServicio;
import org.quartz.SchedulerException;
import repositorios.incidentes.RepoIncidentes;
import services.notificador.GeneradorNotificaciones;

import java.util.*;

public class IncidentesService {

  private RepoIncidentes repoIncidentes;
  private GeneradorNotificaciones servicioGeneradorNotificaciones;

  public IncidentesService(RepoIncidentes repoIncidentes, GeneradorNotificaciones servicioGeneradorNotificaciones){
    this.repoIncidentes = repoIncidentes;
    this.servicioGeneradorNotificaciones = servicioGeneradorNotificaciones;
  }

  public void darDeAltaIncidente(Miembro miembro, PrestacionDeServicio prestacion,String observaciones) throws SchedulerException {

    List<Incidente> incidentes = CreadorDeIncidentes.darDeAltaIncidente(miembro,prestacion,observaciones);
    repoIncidentes.add(incidentes.toArray(new Incidente[0]));

    Set<Miembro> miembrosANotificar = new HashSet<>();

    incidentes.forEach(incidente -> {
      miembrosANotificar.addAll(miembrosInteresados(incidente));
    });

    miembrosANotificar.forEach(miembroANotificar -> {
      servicioGeneradorNotificaciones.generarNotificacion(miembroANotificar.getUsuario(),"Se abrio el incidente: Descripcion" + observaciones);
    });

  }

  public void darDeBajaIncidentesDeLaPrestacion(Miembro miembro, PrestacionDeServicio prestacion){

    List<Incidente> incidentes = repoIncidentes.getByPrestacion(prestacion);

    List<Comunidad> comunidadesDelMiembroCerrador = miembro.comunidades();

    List<Incidente> incidentesDeLasComunidadesDelMiembro = incidentes.stream().filter(incidente -> comunidadesDelMiembroCerrador.contains(incidente.getComunidad())).toList();
    incidentesDeLasComunidadesDelMiembro.forEach(incidente -> incidente.cerrar(miembro));

    Set<Miembro> miembrosANotificar = new HashSet<>();

    incidentes.forEach(incidente -> {
      miembrosANotificar.addAll(miembrosInteresados(incidente));
    });

    miembrosANotificar.forEach(miembroANotificar -> {
      servicioGeneradorNotificaciones.generarNotificacion(miembroANotificar.getUsuario(),"Se cerro el incidente");
    });

  }

  List<Miembro> miembrosInteresados(Incidente incidente){

    List<Comunidad> comunidadesDelMiembroCerrador = incidente.getMiembroApertura().comunidades();

    List<Miembro> listaMiembrosInteresados = new ArrayList<>();
    comunidadesDelMiembroCerrador.forEach(comunidad -> {
          listaMiembrosInteresados.addAll(comunidad.getMiembros());
        }
    );

    return listaMiembrosInteresados;
  }
}
