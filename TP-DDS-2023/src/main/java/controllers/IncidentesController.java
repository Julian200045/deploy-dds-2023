package controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.entities.incidentes.Incidente;
import models.repositorios.RepositorioIncidentes;
import server.utils.ICrudViewsHandler;

public class IncidentesController implements ICrudViewsHandler {

  private RepositorioIncidentes repositorioDeIncidentes;

  public IncidentesController(RepositorioIncidentes repositorioDeIncidentes) {
    this.repositorioDeIncidentes = repositorioDeIncidentes;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    //String filtroPorNombre = context.queryParam("filtro");
    // this.repositorioDeIncidente.getAll(filtroPorNombre);

    List<Incidente> incidentes = this.repositorioDeIncidentes.buscarTodos();

    model.put("incidentes", incidentes);

    context.render("incidentes.hbs", model);
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
