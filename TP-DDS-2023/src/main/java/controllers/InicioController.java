package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.comunidades.Persona;
import models.entities.incidentes.Incidente;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioPersonas;
import models.repositorios.RepositorioUsuarios;
import models.services.IncidentesService;
import server.dtos.IncidenteInicioDto;
import server.utils.ICrudViewsHandler;

public class InicioController implements ICrudViewsHandler {

  private RepositorioIncidentes repositorioIncidentes;
  private IncidentesService incidentesService;
  private RepositorioPersonas repositorioPersonas;
  private RepositorioUsuarios repositorioUsuarios;

  public InicioController(RepositorioIncidentes repositorioIncidentes,
                          RepositorioPersonas repositorioPersonas,
                          RepositorioUsuarios repositorioUsuarios,
                          IncidentesService incidentesService) {
    this.repositorioIncidentes = repositorioIncidentes;
    this.incidentesService = incidentesService;
    this.repositorioPersonas = repositorioPersonas;

    this.repositorioUsuarios = repositorioUsuarios;

  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    List<Incidente> incidentes;
    String estado;

    if (context.queryParamMap().size() <= 1) {
      incidentes = this.repositorioIncidentes.buscarTodos();
      estado = "ABIERTO";
    } else {
      incidentes = this.repositorioIncidentes.buscarTodosFiltrados(
          context.queryParam("establecimiento"),
          context.queryParam("servicio"),
          context.queryParam("comunidad"));
      estado = context.queryParam("estado");
    }

    incidentes = incidentes.stream().filter(incidente -> incidente.getEstado().toString().equals(estado)).toList();

    List<IncidenteInicioDto> incidentesDtos = incidentes.stream().map(incidente ->
        new IncidenteInicioDto(
            incidente.getId(),
            incidente.getPrestacionDeServicio().getServicio().getNombre(),
            incidente.getPrestacionDeServicio().getEstablecimiento().getNombre(),
            incidente.getComunidad().getNombre(),
            incidente.getObservaciones(),
            incidente.getEstado().toString()
        )).toList();

    //ELIMINAR
    Gson gson = new Gson();
    System.out.println(gson.toJson(incidentesDtos));
    //

    model.put("incidentes", incidentesDtos);
    context.render("inicio.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

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

    context.redirect("/inicio", HttpStatus.OK);
  }

  @Override
  public void delete(Context context) {

  }
}
