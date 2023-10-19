package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import models.entities.comunidades.Persona;
import models.entities.incidentes.Incidente;
import models.entities.servicios.PrestacionDeServicio;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioPrestacionesDeServicio;
import models.repositorios.RepositorioUsuarios;
import models.services.IncidentesService;
import org.quartz.SchedulerException;
import server.dtos.DireccionDto;
import server.dtos.IncidenteInicioDto;
import server.dtos.ServiciosPorEstablecimientoDto;
import server.utils.ICrudViewsHandler;

public class IncidentesController implements ICrudViewsHandler {

  private RepositorioIncidentes repositorioIncidentes;
  private IncidentesService incidentesService;
  private RepositorioPersonas repositorioPersonas;
  private RepositorioUsuarios repositorioUsuarios;
  private RepositorioPrestacionesDeServicio repositorioPrestaciones;

  public IncidentesController(RepositorioIncidentes repositorioIncidentes,
                              RepositorioPersonas repositorioPersonas,
                              RepositorioUsuarios repositorioUsuarios,
                              RepositorioPrestacionesDeServicio repositorioPrestaciones,
                              IncidentesService incidentesService) {
    this.repositorioIncidentes = repositorioIncidentes;
    this.incidentesService = incidentesService;
    this.repositorioPersonas = repositorioPersonas;

    this.repositorioUsuarios = repositorioUsuarios;
    this.repositorioPrestaciones = repositorioPrestaciones;

  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    List<Incidente> incidentes;

    String estado = context.queryParam("estado");
    String establecimiento = context.queryParam("establecimiento");
    String servicio = context.queryParam("servicio");
    String comunidad = context.queryParam("comunidad");

    if (context.queryString() == null || context.queryString().equals("")) {
      incidentes = this.repositorioIncidentes.buscarTodos();
      estado = "ABIERTO";
    } else if ((establecimiento == null || establecimiento.equals("")) &&
        (servicio == null || servicio.equals("")) &&
        (comunidad == null || comunidad.equals(""))) {
      incidentes = this.repositorioIncidentes.buscarTodos();
    } else {
      incidentes = this.repositorioIncidentes.buscarTodosFiltrados(
          context.queryParam("establecimiento"),
          context.queryParam("servicio"),
          context.queryParam("comunidad"));
    }

    String finalEstado = estado;
    incidentes = incidentes.stream().filter(incidente -> incidente.getEstado().toString().equals(finalEstado)).toList();

    List<IncidenteInicioDto> incidentesDtos = incidentes.stream().map(incidente ->
        new IncidenteInicioDto(
            incidente.getId(),
            incidente.getPrestacionDeServicio().getServicio().getNombre(),
            incidente.getPrestacionDeServicio().getEstablecimiento().getNombre(),
            incidente.getComunidad().getNombre(),
            incidente.getObservaciones(),
            incidente.getEstado().toString().equals("ABIERTO")
        )).toList();

    model.put("incidentes", incidentesDtos);
    context.render("incidentes.hbs", model);
  }

  @Override
  public void show(Context context) {
    Gson gson = new Gson();

    Map<String, Object> model = new HashMap<>();

    String id = context.pathParam("id");

    Incidente incidente = (Incidente) this.repositorioIncidentes.buscar(Long.parseLong(id));

    IncidenteInicioDto incidenteDto = new IncidenteInicioDto(
        incidente.getId(),
        incidente.getPrestacionDeServicio().getServicio().getNombre(),
        incidente.getPrestacionDeServicio().getEstablecimiento().getNombre(),
        incidente.getComunidad().getNombre(),
        incidente.getObservaciones(),
        incidente.getEstado().toString().equals("ABIERTO"));

    DireccionDto direccionDto =
        new DireccionDto(
            incidente.getPrestacionDeServicio().getEstablecimiento().getCalle(),
            incidente.getPrestacionDeServicio().getEstablecimiento().getAltura());

    model.put("incidente", gson.toJson(incidenteDto));
    model.put("direccion", gson.toJson(direccionDto));

    context.render("revision_incidente.hbs", model);
  }

  @Override
  public void create(Context context) {
    Gson gson = new Gson();

    Map<String, Object> model = new HashMap<>();

    List<PrestacionDeServicio> prestaciones = repositorioPrestaciones.buscarTodos();

    List<ServiciosPorEstablecimientoDto> serviciosPorEstablecimientoDto = new ArrayList<>();

    prestaciones.stream().map(prestacion -> prestacion.getEstablecimiento()).collect(Collectors.toSet()).forEach(establecimiento -> {

      List<PrestacionDeServicio> prestacionesDelEstablecimiento = (List<PrestacionDeServicio>) repositorioPrestaciones.buscarPorEstablecimiento(establecimiento.getId());
      serviciosPorEstablecimientoDto.add(
          new ServiciosPorEstablecimientoDto(
              establecimiento.getNombre(),
              prestacionesDelEstablecimiento.stream().map(prestacion -> prestacion.getServicio().getNombre()).toList()));
    });

    String jsonServiciosPorEstablecimiento = gson.toJson(serviciosPorEstablecimientoDto);
    model.put("serviciosPorEstablecimiento", jsonServiciosPorEstablecimiento);

    context.render("alta-incidente.hbs", model);
  }

  @Override
  public void save(Context context) throws SchedulerException {
    Map<String, Object> model = new HashMap<>();

    //ELIMINAR
    context.sessionAttribute("idUsuario", 4L);
    Long idUsuarioEnSesion = context.sessionAttribute("idUsuario");
    //

    Usuario usuarioEnSesion = (Usuario) repositorioUsuarios.buscar(idUsuarioEnSesion);
    Persona personaEnSesion = (Persona) repositorioPersonas.buscarPorUsuario(usuarioEnSesion);

    String nombreEstablecimiento = context.formParam("establecimiento");
    String nombreServicio = context.formParam("servicio");
    String observaciones = context.formParam("observaciones");

    PrestacionDeServicio prestacion = (PrestacionDeServicio) repositorioPrestaciones.buscarPorEstablecimientoYServicio(nombreEstablecimiento, nombreServicio);

    incidentesService.darDeAltaIncidente(personaEnSesion, prestacion, observaciones);
    context.redirect("/incidentes/crear");
  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {
    //ELIMINAR
    context.sessionAttribute("idUsuario", 4L);
    Long idUsuarioEnSesion = context.sessionAttribute("idUsuario");
    //

    Long idIncidente = Long.parseLong(context.pathParam("id"));

    Usuario usuarioEnSesion = (Usuario) repositorioUsuarios.buscar(idUsuarioEnSesion);
    Persona personaEnSesion = (Persona) repositorioPersonas.buscarPorUsuario(usuarioEnSesion);
    Incidente incidenteADarDeBaja = (Incidente) repositorioIncidentes.buscar(idIncidente);

    incidentesService.darDeBajaIncidentesDeLaPrestacion(personaEnSesion, incidenteADarDeBaja.getPrestacionDeServicio());
  }

  @Override
  public void delete(Context context) {

  }
}
