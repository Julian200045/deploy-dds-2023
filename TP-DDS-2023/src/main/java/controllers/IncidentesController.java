package controllers;

import io.javalin.http.Context;
import java.util.List;
import models.repositorios.incidentes.RepositorioIncidentes;
import models.entities.incidentes.*;

public class IncidentesController {

  private RepositorioIncidentes repositorioDeIncidentes;

  public IncidentesController (RepositorioIncidentes repositorioDeIncidentes) {
    this.repositorioDeIncidentes = repositorioDeIncidentes;
  }
  public void index(Context context) {
    List<Incidente> incidentes = repositorioDeIncidentes.getAll();
    context.result("Devuelvo un listado de todos los incidentes");
  }
}
