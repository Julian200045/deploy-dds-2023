package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import models.entities.incidentes.Incidente;
import models.repositorios.RepositorioIncidentes;
import server.dtos.IncidenteInicioDto;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AltaIncidenteController implements ICrudViewsHandler {

  private RepositorioIncidentes repositorioIncidentes;

  public AltaIncidenteController(RepositorioIncidentes repositorioIncidentes) {
    this.repositorioIncidentes = repositorioIncidentes;
  }

  @Override
  public void index(Context context) {

    context.render("alta-incidente.hbs",new HashMap<>());
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
