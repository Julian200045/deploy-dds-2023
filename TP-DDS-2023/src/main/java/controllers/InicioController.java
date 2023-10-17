package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import models.entities.incidentes.EstadoIncidente;
import models.entities.incidentes.Incidente;
import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioTipoEntidad;
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

  public InicioController(RepositorioIncidentes repositorioIncidentes) {
    this.repositorioIncidentes = repositorioIncidentes;
  }

  @Override
  public void index(Context context) {
    Map<String,Object> model = new HashMap<>();

    List<Incidente> incidentes = new ArrayList<>();

    if (context.queryParamMap().isEmpty()){
      incidentes = this.repositorioIncidentes.buscarTodos();
    }
    else {
      System.out.println("ENTRO A ELSE");
      incidentes = this.repositorioIncidentes.buscarTodosFiltrados(
          context.queryParam("establecimiento_id"),
          context.queryParam("servicio_id"),
          context.queryParam("comunidad_id"),
          context.queryParam("estado"));
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

    Gson gson = new Gson();

    System.out.println(gson.toJson(incidentesDtos));

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

  }

  @Override
  public void delete(Context context) {

  }
}
