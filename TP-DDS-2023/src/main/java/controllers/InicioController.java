package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import models.entities.comunidades.Persona;
import models.entities.incidentes.EstadoIncidente;
import models.entities.incidentes.Incidente;
import models.entities.usuarios.Usuario;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioTipoEntidad;
import models.repositorios.RepositorioUsuarios;
import models.repositorios.personas.RepositorioPersonas;
import models.repositorios.personas.RepositorioPersonas;
import models.services.IncidentesService;
import server.dtos.IncidenteInicioDto;
import server.utils.ICrudViewsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InicioController implements ICrudViewsHandler {

  private RepositorioIncidentes repositorioIncidentes;
  private IncidentesService incidentesService;
  private RepositorioPersonas repositorioPersonas;

  public InicioController(RepositorioIncidentes repositorioIncidentes,
                          RepositorioPersonas repositorioPersonas,
                          IncidentesService incidentesService) {
    this.repositorioIncidentes = repositorioIncidentes;
    this.incidentesService = incidentesService;
    this.repositorioPersonas = repositorioPersonas;
  }

  @Override
  public void index(Context context) {
    Map<String,Object> model = new HashMap<>();

    List<Incidente> incidentes;

    if (context.queryParamMap().isEmpty()){
      incidentes = this.repositorioIncidentes.buscarTodos();
    }
    else {
      incidentes = this.repositorioIncidentes.buscarTodosFiltrados(
          context.queryParam("establecimiento"),
          context.queryParam("servicio"),
          context.queryParam("comunidad"));
    }

    if(context.queryParam("estado") != null){
    incidentes = incidentes.stream().filter(incidente -> {
      return incidente.getEstado().toString().equals(context.queryParam("estado"));
    }).toList();
    }

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

    model.put("incidentes",incidentesDtos);
    context.render("inicio.hbs",model);
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
    Map<String,Object> model = new HashMap<>();

    //ELIMINAR
    context.sessionAttribute("idUsuario",1L);
    Long idUsuarioEnSesion = context.sessionAttribute("idUsuario");
    //

    Long idIncidente = Long.parseLong(context.pathParam("id"));

    Persona personaEnSesion = (Persona) repositorioPersonas.buscar(idUsuarioEnSesion);
    Incidente incidenteADarDeBaja = (Incidente) repositorioIncidentes.buscar(idIncidente);

    incidentesService.darDeBajaIncidentesDeLaPrestacion(personaEnSesion, incidenteADarDeBaja.getPrestacionDeServicio());
  }

  @Override
  public void delete(Context context) {

  }
}
