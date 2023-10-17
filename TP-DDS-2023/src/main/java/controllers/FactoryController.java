package controllers;

import models.repositorios.RepositorioIncidentes;
import models.repositorios.RepositorioNotificaciones;
import models.repositorios.RepositorioPersonas;
import models.services.IncidentesService;
import models.services.notificador.GeneradorNotificaciones;

public class FactoryController {

  public static Object controller(String nombre) {
    Object controller = null;

    switch (nombre) {
      case "Inicio": controller = new InicioController(new RepositorioIncidentes(),
                                                       new RepositorioPersonas(),
                                                       new IncidentesService(new RepositorioIncidentes(),new GeneradorNotificaciones(new RepositorioNotificaciones()))
                                                       ); break;
      case "AltaIncidente": controller = new AltaIncidenteController(new RepositorioIncidentes()); break;
    }

    return controller;
  }
}
